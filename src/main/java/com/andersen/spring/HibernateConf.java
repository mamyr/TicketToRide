package com.andersen.spring;

import com.andersen.spring.controllers.RouteController;
import com.andersen.spring.controllers.TicketController;
import com.andersen.spring.services.CityDaoImpl;
import com.andersen.spring.services.RouteDaoImpl;
import com.andersen.spring.services.TicketDaoImpl;
import jakarta.persistence.EntityManagerFactory;

import jakarta.persistence.PersistenceUnit;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

/**
 *Implementation of the configuration for the hibernate mapping with Spring application.
 *
 *<p>The {@code @Configuration} is a class-level annotation indicating that an object is a source of bean definitions.
 *The {@code @Configuration} class declares beans through {@code @Bean}-annotated methods.
 *
 *<p>Spring {@code @PropertySource} annotation is a convenient mechanism for adding property sources to the environment.
 *Reads property resource from path {@code "classpath:/config/application.properties"}.
 *
 * <p> The {@code @PersistenceUnit} annotation from package {@link jakarta.persistence.PersistenceUnit} expresses a dependency on an {@code EntityManagerFactory} and its associated persistence unit.
 *
 * <p>The {@code dataSource}, {@code sessionFactory}, {@code hibernateProperties}, {@code entityManagerFactory},
 * {@code jpaVendorAdapter}, {@code cityDao}, {@code routeDao}, {@code ticketDao}, {@code ticketController}, {@code routeController} operations run in O(1) time.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/package-info.html">HibernateConf</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@Configuration
@PropertySource("classpath:/config/application.properties")
public class HibernateConf {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code dataSource()} is related with bean named {@code dataSource}.
     * Spring {@code @Scope} annotation controls the scope of the object created from a particular bean definition.
     * The default scope of bean is {@code "singleton"}. Scopes a single bean definition to a single object instance for each Spring IoC container.
     * The method {@code dataSource()} returns the {@code DriverManagerDataSource} object.
     * @return DriverManagerDataSource
     */
    @Bean(name = "dataSource")
    @Scope("singleton")
    public static DriverManagerDataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ticketToRideDb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code sessionFactory()} is related with bean named {@code sessionFactory}.
     * The method {@code sessionFactory()} returns the {@code SessionFactory} object.
     * @return SessionFactory
     */
    @Bean(name = "sessionFactory")
    public static SessionFactory sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[]{"com.andersen.hibernate.Models", "com.andersen.spring.services"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        sessionFactory.setMetadataSources(new MetadataSources(ssr));
        Metadata meta = sessionFactory.getMetadataSources().getMetadataBuilder().build();

        return meta.getSessionFactoryBuilder().build();
    }

    /**
     * The method {@code hibernateProperties()} returns the hibernate {@code Properties} object.
     * @return Properties
     */
    private static final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty(
                "hibernate.show_sql", "true");
        hibernateProperties.setProperty(
                "hibernate.format_sql", "true");
        hibernateProperties.setProperty(
                "hibernate.current_session_context_class", "thread");
        hibernateProperties.setProperty(
                "hibernate.id.new_generator_mappings", "true");
        hibernateProperties.setProperty(
                "hibernate.connection.autocommit", "true");

        return hibernateProperties;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code entityManagerFactory()} is related with bean named {@code entityManagerFactory}.
     * Spring {@code @Primary} annotation is used to give higher preference to a bean when there are multiple beans of the same type.
     * The method {@code entityManagerFactory()} returns the {@code EntityManagerFactory} object.
     * @return EntityManagerFactory
     */
    @Primary
    @Bean(name = "entityManagerFactory")
    public static EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());//(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("com.andersen.hibernate.Models", "com.andersen.spring.services");
        emf.setPersistenceUnitName("entityManagerFactoryUnit");
        //** Set the JPA provider explicitly (Hibernate)
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.afterPropertiesSet();
        return (EntityManagerFactory) emf.getObject();
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code jpaVendorAdapter()} is related with bean named {@code jpaVendorAdapter}.
     * The method {@code jpaVendorAdapter()} returns the {@code HibernateJpaVendorAdapter} object.
     * @return HibernateJpaVendorAdapter
     */
    @Bean(name = "jpaVendorAdapter")
    public static HibernateJpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        return jpaVendorAdapter;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code cityDao()} is related with bean named {@code cityDao}.
     * The method {@code cityDao()} returns the {@code CityDaoImpl} object.
     * @return CityDaoImpl
     */
    @Bean(name = "cityDao")
    public static CityDaoImpl cityDao() {
        CityDaoImpl cityDao = new CityDaoImpl();
        cityDao.setTemplate(sessionFactory());

        return cityDao;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code routeDao()} is related with bean named {@code routeDao}.
     * The method {@code routeDao()} returns the {@code RouteDaoImpl} object.
     * @return RouteDaoImpl
     */
    @Bean(name = "routeDao")
    public static RouteDaoImpl routeDao() {
        RouteDaoImpl routeDao = new RouteDaoImpl();
        routeDao.setTemplate(sessionFactory());

        return routeDao;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code ticketDao()} is related with bean named {@code ticketDao}.
     * The method {@code ticketDao()} returns the {@code TicketDaoImpl} object.
     * @return TicketDaoImpl
     */
    @Bean(name = "ticketDao")
    public static TicketDaoImpl ticketDao() {
        TicketDaoImpl ticketDAO = new TicketDaoImpl();
        ticketDAO.setTemplate(sessionFactory());

        return ticketDAO;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code ticketController()} is related with bean named {@code ticketController}.
     * The method {@code ticketController()} returns the {@code TicketController} object.
     * @return TicketController
     */
    @Bean(name = "ticketController")
    public static TicketController ticketController() {
        TicketController controller = new TicketController(ticketDao());
        controller.setCityTemplate(cityDao());
        controller.setRouteTemplate(routeDao());
        return controller;
    }

    /**
     * Spring {@code @Bean} annotation is a method-level annotation and a direct analog of the XML <bean/> element.
     * The method {@code routeController()} is related with bean named {@code routeController}.
     * The method {@code routeController()} returns the {@code RouteController} object.
     * @return RouteController
     */
    @Bean(name = "routeController")
    public static RouteController routeController() {
        RouteController controller = new RouteController(routeDao());
        controller.setCityTemplate(cityDao());
        return controller;
    }
}
