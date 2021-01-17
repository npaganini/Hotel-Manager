package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.models.user.UserRole;
import ar.edu.itba.paw.webapp.auth.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:key.properties")
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAuthConfig.class);

    @Value(value = "${key}")
    private String key;

    private final CustomUserDetailsService userDetailsService;
    private final TokenAuthHandlerService tokenAuthHandlerService;

    @Autowired
    public WebAuthConfig(CustomUserDetailsService userDetailsService, TokenAuthHandlerService tokenAuthHandlerService) {
        this.userDetailsService = userDetailsService;
        this.tokenAuthHandlerService = tokenAuthHandlerService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilterBean() throws Exception {
        return new AuthenticationFilter(authenticationManagerBean(), tokenAuthHandlerService);
    }

    @Bean
    public AuthorizationFilter authorizationFilterBean() throws Exception {
        return new AuthorizationFilter(authenticationManagerBean(), tokenAuthHandlerService, userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http.authorizeRequests()
            .antMatchers("/login")
            .permitAll()
            .antMatchers("/user/**")
            .hasAuthority(UserRole.CLIENT.toString())
            .antMatchers("/rooms/**", "/reservation/**", "/products/**", "/ratings/**")
            .hasAnyAuthority(UserRole.EMPLOYEE.toString(), UserRole.MANAGER.toString())
            .antMatchers("/", "/index", "/product/**")
            .hasAnyAuthority(UserRole.EMPLOYEE.toString(), UserRole.MANAGER.toString(), UserRole.CLIENT.toString())
            .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
        http.addFilter(authenticationFilterBean()).addFilter(authorizationFilterBean());
        // simple cors filter is used to add headers that axios needed
        http.addFilterBefore(new SimpleCorsFilter(), AbstractPreAuthenticatedProcessingFilter.class);
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(
        "/css/**", "/js/**", "/img/**", "/bootstrap/**", "/utilities/**", "/favicon.ico", "/403"
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
