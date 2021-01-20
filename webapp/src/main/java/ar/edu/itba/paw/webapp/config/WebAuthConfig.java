package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.models.user.UserRole;
import ar.edu.itba.paw.webapp.auth.CustomUserDetailsService;
import ar.edu.itba.paw.webapp.auth.MyUserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:key.properties")
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAuthConfig.class);

    @Value(value = "${key}")
    private String key;

    private final CustomUserDetailsService customUserDetailsService;
    private final ServletContext servletContext;

    @Autowired
    public WebAuthConfig(CustomUserDetailsService customUserDetailsService, ServletContext servletContext) {
        this.customUserDetailsService = customUserDetailsService;
        this.servletContext = servletContext;
    }

    // FIXME
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // simple cors filter is used to add headers that axios needed
        http.addFilterBefore(new SimpleCorsFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/login").anonymous()
//                .antMatchers("/user/**").hasAuthority(UserRole.CLIENT.toString())
//                .antMatchers("/rooms/**", "/reservation/**", "/products/**").hasAnyAuthority(UserRole.EMPLOYEE.toString(), UserRole.MANAGER.toString())
//                .antMatchers("/product/**").hasAnyAuthority(UserRole.EMPLOYEE.toString(), UserRole.MANAGER.toString(), UserRole.CLIENT.toString())
//                .and().rememberMe()
//                .rememberMeParameter("rememberMe")
//                .userDetailsService(customUserDetailsService)
//                .key(key)
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toMinutes(60))
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
                .and().exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .csrf()
                .disable();
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/bootstrap/**", "/utilities/**", "/favicon.ico", "/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

}
