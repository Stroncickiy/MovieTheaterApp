package com.epam.moovies.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.epam.moovies.service.UserDetailsServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.epam.moovies")
@EnableAspectJAutoProxy
@PropertySources({ @PropertySource("classpath:auditories.properties"),
		@PropertySource("classpath:environment.properties") })
public class ApplicationConfig {

	private static final String DB_NAME = "movieTheatre";
	@Value("${db.port}")
	private String port;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;

	@Value("${db.restoreFromDump}")
	private boolean restoreFromDump;

	@Value("${db.dumpName}")
	private String dumpFileName;

	@Value("${db.serverLocationFolder}")
	private String serverLocationFolder;

	@PostConstruct
	public void initDataBase() throws IOException {
		if (restoreFromDump) {
			Resource resource = new ClassPathResource(dumpFileName);
			File dumpFile = resource.getFile();
			restoreDB(serverLocationFolder, DB_NAME, username, password, dumpFile.getAbsolutePath());
		}
	}

	public static boolean restoreDB(String serverLocationFolder, String dbName, String dbUserName, String dbPassword,
			String source) throws IOException {

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", serverLocationFolder + "\\mysql", "--user=" + dbUserName,
				"--password=" + dbPassword, dbName, "-e", " source " + source);
		builder.redirectErrorStream(true);
		Process runtimeProcess = builder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
		String line;
		while (true) {
			line = reader.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}

		try {
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup restored successfully");
				return true;
			} else {
				System.out.println("Could not restore the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
;
		return false;
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://localhost:" + port + "/" + DB_NAME + "?autoReconnect=true&useSSL=false&create=true");
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		return factory;
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("128KB");
		factory.setMaxRequestSize("128KB");
		return factory.createMultipartConfig();
	}

}
