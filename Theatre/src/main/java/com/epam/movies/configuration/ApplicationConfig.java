package com.epam.movies.configuration;

import com.epam.movies.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackages = "com.epam.movies")
@EnableAspectJAutoProxy
@PropertySources({@PropertySource("classpath:auditoriums.properties"),
        @PropertySource("classpath:environment.properties")})
public class ApplicationConfig {

    private static final String DB_NAME = "movietheater";
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

    private static final String configFileName = "environment.properties";

    @PostConstruct
    public void initDataBase() throws IOException {
        if (restoreFromDump) {
            Resource resource = new ClassPathResource(dumpFileName);
            File dumpFile = resource.getFile();
            boolean isDbRestoringSuccessful = restoreDB(serverLocationFolder, DB_NAME, username, password, dumpFile.getAbsolutePath());
            if (isDbRestoringSuccessful) {
                Resource envConfigFileResource = new ClassPathResource(configFileName);
                File envConfigFile = resource.getFile();
                Properties properties = new Properties();
                properties.load(new FileInputStream(envConfigFile));
                properties.replace("db.restoreFromDump", "false");
                properties.store(new FileOutputStream(envConfigFile), "changed init param");
            }
        }
    }

    public static boolean restoreDB(String serverLocationFolder, String dbName, String dbUserName, String dbPassword,
                                    String source) throws IOException {

        ProcessBuilder createDbCommandBuilder = new ProcessBuilder("cmd", "/c", "cd", "\"" + serverLocationFolder + "\"", "&&", "mysql", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "\"create database " + dbName + "\"");
        ProcessBuilder restoreFromDumpCommandBuilder = new ProcessBuilder("cmd", "/c", "cd", "\"" + serverLocationFolder + "\"", "&&", "mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "<", "\"" + source + "\"");

        boolean isDbRestored = false;
        boolean isDbCreated;
        isDbCreated = executeProcess(createDbCommandBuilder, "db Created", "db creating FAILED");
        if (isDbCreated) {
            isDbRestored = executeProcess(restoreFromDumpCommandBuilder, "db Restored", "db restoring FAILED");
        }
        return isDbCreated && isDbRestored;
    }

    private static boolean executeProcess(ProcessBuilder processBuilder, String successMsg, String failedMsg) throws IOException {
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        try {
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println(successMsg);
                return true;
            } else {
                System.out.println(failedMsg);
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
