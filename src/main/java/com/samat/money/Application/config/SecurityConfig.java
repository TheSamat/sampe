package com.samat.money.Application.config;

import com.samat.money.Application.filter.InitFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final InitFilter initFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()

                .anyRequest()
                .permitAll()
//                .antMatchers("/auth",
//
//                        "/swagger-ui/",
//                        "/swagger-ui/**",
//                        "/swagger-resources/**",
//                        "/v2/**",
//                        "/v3/**",
//                        "/webjars/**"
//                )
//                .permitAll()
//
//                .anyRequest()
//                .authenticated()


                .and()
                .exceptionHandling()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(initFilter, SecurityContextPersistenceFilter.class);
        http.cors();
    }
}
