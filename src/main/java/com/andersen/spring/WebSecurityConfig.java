package com.andersen.spring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *Implementation of the configuration for the Spring security.
 *
 *<p>The {@code @Configuration} is a class-level annotation indicating that an object is a source of bean definitions.
 *The {@code @Configuration} class declares beans through {@code @Bean}-annotated methods.
 *
 *<p>Spring {@code @EnableWebSecurity} annotation is added to an @Configuration class to have the Spring Security configuration defined in any WebSecurityConfigurer.
 * The attribute {@code debug} is enabled to log messages at debugging level.
 *
 * <p>Spring {@code EnableMethodSecurity} annotation enables Spring Security support for modeling at the method level
 *
 * <p>The {@code securityFilterChain, userDetailsService, passwordEncoder, authenticationProvider, remoteUser} operation run in O(1) time.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/package-info.html">WebSecurityConfig</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@Configuration
@EnableWebSecurity(debug=true)
@EnableMethodSecurity
public class WebSecurityConfig {
    /**
     * The method {@code securityFilterChain} accepts {@code HttpSecurity} object and defines which routes must be authenticated and which roles of users are authorised for accessing routes.
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/api/route", "/api/ticket/**").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    /**
     * The method {@code userDetailsService} overrides the default definition for username and password details of {@code UserDetailsService} object.
     * @return UserDetailsService
     */
    @Bean(name = "userDetailsServiceImpl")
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * The method {@code passwordEncoder} returns initialised {@code BCryptPasswordEncoder} object.
     * @return BCryptPasswordEncoder
     */
    @Bean(name = "encoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * The method {@code authenticationProvider} accepts {@code UserDetailsService} for initialising {@code DaoAuthenticationProvider}
     * @param myUserService
     * @return DaoAuthenticationProvider
     */
    @Bean(name = "authProvider")
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService myUserService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(myUserService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * The method {@code remoteUser} accepts {@code HttpServletRequest} to get remote username;
     * @param request
     * @return String
     */
    @ModelAttribute("remoteUser")
    public Object remoteUser(final HttpServletRequest request) {
        return request.getRemoteUser();
    }
}
