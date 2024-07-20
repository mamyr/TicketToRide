package com.andersen.spring;

import com.andersen.spring.controllers.RouteController;
import com.andersen.spring.controllers.TicketController;
import com.andersen.spring.services.CityDaoImpl;
import com.andersen.spring.services.RouteDaoImpl;
import com.andersen.spring.services.TicketDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *Implementation of the {@code CommandLineRunner} for the Spring application.
 *
 *<p>The {@code @ComponentScan} annotation specifies the packages that must be scanned for bean definitions.
 * The attribute {@code basePackages} defines which packages must be scanned.
 *
 *<p>Spring {@code @EnableJpaRepositories} is used to enable JPA repositories.
 *Will scan the package of the annotated configuration class for Spring Data repositories by default.
 * The attribute {@code basePackages} defines which packages must be scanned.
 *
 * <p> The {@code @EntityScan} annotation is used when entity classes are not placed in the main application package or its sub-packages
 *
 * <p> The {@code @SpringBootApplication} annotation can be used to enable three features, that is:
 *<ul>
 * <li>@EnableAutoConfiguration: enable Spring Boot’s auto-configuration mechanism
 * <li>@ComponentScan: enable @Component scan on the package where the application is located (see the best practices)
 * <li>@Configuration: allow to register extra beans in the context or import additional configuration classes
 *</ul>
 * Here used attribute {@code exclude} is to disable specific auto-configuration classes that application do not want being applied.
 * These are disabled auto-configurations: {@code DataSourceAutoConfiguration, LiquibaseAutoConfiguration, JooqAutoConfiguration, R2dbcAutoConfiguration, UserDetailsServiceAutoConfiguration}
 *
 *<p>Spring {@code @Import} is used to enable different configurations in spring application. It has an explicit approach.
 * These are explicitly added configurations: {@code HibernateConf, MvcConfig, WebSecurityConfig}
 *
 * <p>Spring {@code @EnableTransactionManagement} enables Spring's annotation-driven transaction management capability, similar to the support found in Spring's <tx:*> XML namespace.
 *
 * <p>The field {@code cityDao} is connected with the bean {@code cityDao} with the annotation {@code @Autowired} and is of type {@code CityDaoImpl}.
 * <p>The field {@code routeDao} is connected with the bean {@code routeDao} with the annotation {@code @Autowired} and is of type {@code RouteDaoImpl}.
 * <p>The field {@code ticketDao} is connected with the bean {@code ticketDao} with the annotation {@code @Autowired} and is of type {@code TicketDaoImpl}.
 * <p>The field {@code ticketController} is connected with the bean {@code ticketController} with the annotation {@code @Autowired} and is of type {@code TicketController}.
 * <p>The field {@code routeController} is connected with the bean {@code routeController} with the annotation {@code @Autowired} and is of type {@code RouteController}.
 *
 * <p>The {@code run}, {@code main} operations run in O(1) time.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/package-info.html">MappingApplicationSpring</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@ComponentScan(basePackages = {"com.andersen.spring.controllers.*, com.andersen.spring.repositories.*, com.andersen.spring.services.*, com.andersen.hibernate.Models.*"})
@EnableJpaRepositories(basePackages = "com.andersen.spring.services.*, com.andersen.spring.repositories.*")
@EntityScan("com.andersen.hibernate.Models.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, LiquibaseAutoConfiguration.class, JooqAutoConfiguration.class, R2dbcAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@Import({ HibernateConf.class, MvcConfig.class, WebSecurityConfig.class})
@EnableTransactionManagement
public class MappingApplicationSpring implements CommandLineRunner {
    @Autowired
    private static CityDaoImpl cityDao;
    @Autowired
    private static RouteDaoImpl routeDao;
    @Autowired
    private static TicketDaoImpl ticketDao;
    @Autowired
    private static TicketController ticketController;
    @Autowired
    private static RouteController routeController;

    /**
     * The {@code Logger} is used for message logging by default on console.
     */
    private static final Logger logger = Logger.getLogger(String.valueOf(MappingApplicationSpring.class));

    /**
     * Overrides the method from {@code CommandLineRunner} and uses annotation {@code @Override} to implement the method.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
    }

    /**
     * The method {@code main} is used to initialise {@code SpringApplication} object by running {@code MappingApplicationSpring} object.
     * The {@code ApplicationContext} is initialised from {@code HibernateConf} class.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MappingApplicationSpring.class, args);

        ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConf.class);

        cityDao = (CityDaoImpl) ctx.getBean("cityDao");
        routeDao = (RouteDaoImpl) ctx.getBean("routeDao");
        ticketDao = (TicketDaoImpl) ctx.getBean("ticketDao");
        routeController = (RouteController) ctx.getBean("routeController");
        ticketController = (TicketController) ctx.getBean("ticketController");

    }
}
