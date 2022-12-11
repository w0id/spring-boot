package ru.gb.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.gb.handlers.SimpleAuthenticationSuccessHandler;
import ru.gb.services.UserDetailService;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailService userDetailService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        String redirectUrl = "/";
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/manage.html").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/users.html").hasRole("ADMIN")
                .antMatchers("/api/v1/users**").hasRole("ADMIN")
                .antMatchers("/users.html").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
                .and()
                .exceptionHandling()
                .accessDeniedPage("/forbidden.html")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        SimpleAuthenticationSuccessHandler simpleAuthenticationSuccessHandler = new SimpleAuthenticationSuccessHandler();
        return simpleAuthenticationSuccessHandler;
    }
}
