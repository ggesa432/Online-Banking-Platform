package com.synergisticit.config;

import com.synergisticit.component.CustomAccessDeniedHandler;
import com.synergisticit.component.CustomAuthenticationSuccessHandler;
import com.synergisticit.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author GesangZeren
 * @project OnlineBank
 * @date 1/16/2025
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login","/WEB-INF/views/**").permitAll()
                        .requestMatchers("/roles","/customers/new","/accounts/new","/users/new","/branches/new",
                                "/customers/{id}/delete","/accounts/{id}/delete","/branches/{id}/delete",
                                "/users/{id}/delete","/accounts/{id}/edit","/branches/{id}/edit").hasAnyRole("ADMIN", "MANAGER")



                        // Branch creation/update/deletion requires ADMIN or MANAGER
                        .requestMatchers(HttpMethod.POST, "/branches/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/branches/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/branches/**").hasAnyRole("ADMIN","MANAGER")

                        // GET /branches/** just requires authentication (any logged-in user can read)
                        .requestMatchers(HttpMethod.GET, "/branches/**").authenticated()

                        .requestMatchers("/transactions/**").authenticated()
                        .requestMatchers("/customers/**").authenticated()
                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().authenticated()
                )

                // Form login config
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler) // Use the custom access denied handler
                )
                // Logout config
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

}
