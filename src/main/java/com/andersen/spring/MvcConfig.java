package com.andersen.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *Implementation of the configuration for the WebMvcConfigurer.
 *
 *<p>The {@code @Configuration} is a class-level annotation indicating that an object is a source of bean definitions.
 *The {@code @Configuration} class declares beans through {@code @Bean}-annotated methods.
 *
 * <p>The {@code addViewControllers} operation run in O(1) time.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/package-info.html">MvcConfig</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * The method {@code addViewControllers} maps route name with view name.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
}
