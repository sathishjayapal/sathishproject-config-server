package me.sathish.sathishprojectconfigserver.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    final BasicAuthBean environment;

    public WebSecurityConfig(BasicAuthBean environment) {
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers(EndpointRequest.toAnyEndpoint())
                                        .permitAll()
                                        .requestMatchers(
                                                "/css/**",
                                                "/js/**",
                                                "/img/**",
                                                "/lib/**",
                                                "/favicon.ico")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .httpBasic(httpBasic -> {})
                .csrf(csrf -> csrf.disable())
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername(environment.getUsername())
                        .password(bCryptPasswordEncoder().encode(environment.getPassword()))
                        .roles("USER")
                        .build());
        manager.createUser(
                User.withUsername(environment.getUsername() + "Admin")
                        .password(bCryptPasswordEncoder().encode(environment.getPassword()))
                        .roles("USER", "ADMIN")
                        .build());
        return manager;
    }
}
