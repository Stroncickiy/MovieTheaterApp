package com.epam.movies.configuration;

import com.epam.movies.converters.LocalDateConverter;
import com.epam.movies.httpconverters.TicketToPdfHttpConverter;
import com.epam.movies.service.impl.UserDetailsServiceImpl;
import com.epam.movies.util.ProcessExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackages = "com.epam.movies.*")
@EnableAspectJAutoProxy
@PropertySources({@PropertySource("classpath:auditoriums.properties"),
        @PropertySource("classpath:environment.properties")})
@EnableWs
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.name}")
    private String dbName;

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

    @Value("${dateFormat}")
    private String dateFormat;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter(dateFormat));
    }


    @PostConstruct
    public void initDataBase() throws IOException {
        if (restoreFromDump) {
            Resource resource = new ClassPathResource(dumpFileName);
            File dumpFile = resource.getFile();
            boolean isDbRestoringSuccessful = restoreDB(serverLocationFolder, dbHost, dbName, username, password,
                    dumpFile.getAbsolutePath());
            if (isDbRestoringSuccessful) {
                Resource envConfigFileResource = new ClassPathResource(configFileName);
                File envConfigFile = envConfigFileResource.getFile();
                Properties properties = new Properties();
                properties.load(new FileInputStream(envConfigFile));
                properties.replace("db.restoreFromDump", "false");
                properties.store(new FileOutputStream(envConfigFile), "changed init param");
            }
        }

    }

    public static boolean restoreDB(String serverLocationFolder, String dbHost, String dbName, String dbUserName, String dbPassword,
                                    String source) throws IOException {

        Resource restoreDbBatFileResource = new ClassPathResource("restoreDb.bat");
        File restoreDbBatFile = restoreDbBatFileResource.getFile();

        ProcessBuilder restoreDbFromFile = new ProcessBuilder("cmd", "/c", "start", restoreDbBatFile.getAbsolutePath(),
                serverLocationFolder, dbHost, dbUserName, dbPassword, dbName, source);

        return ProcessExecutor.executeProcess(restoreDbFromFile, "db restored",
                "fail while restoring db");
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
                "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName + "?autoReconnect=true&useSSL=false&create=true");
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
        return new TomcatEmbeddedServletContainerFactory();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("format")
                .mediaType("pdf", new MediaType("application", "pdf"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }

    @Bean
    public ViewResolver viewResolver2() {
        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
        resourceBundleViewResolver.setOrder(0);
        resourceBundleViewResolver.setBasename("views");
        return resourceBundleViewResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    //WS
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "userService")
    public DefaultWsdl11Definition userServiceWsdl11Definition(@Qualifier("usersSchema") SimpleXsdSchema simpleXsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserService");
        wsdl11Definition.setLocationUri("/ws/userService");
        wsdl11Definition.setTargetNamespace("http://movietheater.epam.com");
        wsdl11Definition.setSchema(simpleXsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "eventService")
    public DefaultWsdl11Definition eventServiceWsdl11Definition(@Qualifier("eventsSchema") SimpleXsdSchema simpleXsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("EventService");
        wsdl11Definition.setLocationUri("/ws/eventService");
        wsdl11Definition.setTargetNamespace("http://movietheater.epam.com");
        wsdl11Definition.setSchema(simpleXsdSchema);
        return wsdl11Definition;
    }

    @Qualifier("usersSchema")
    @Bean
    public SimpleXsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/UserWebService.xsd"));
    }

    @Qualifier("eventsSchema")
    @Bean
    public SimpleXsdSchema eventsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/EventWebService.xsd"));
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new TicketToPdfHttpConverter());
        super.configureMessageConverters(converters);
    }
}
