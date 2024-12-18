package me.sathish.sathishprojectconfigserver.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    final BasicAuthBean environment;

    public WebSecurityConfig(BasicAuthBean environment) {
        this.environment = environment;
    }

    @Value("${spring.security.debug:false}")
    boolean securityDebug;

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

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.debug(securityDebug)
                        .ignoring()
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
