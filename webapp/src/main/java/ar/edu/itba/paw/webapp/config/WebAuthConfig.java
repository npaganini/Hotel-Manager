package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.models.user.UserRole;
import ar.edu.itba.paw.webapp.auth.CustomUserDetailsService;

import ar.edu.itba.paw.webapp.auth.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public WebAuthConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(customUserDetailsService)
                .sessionManagement()
                .invalidSessionUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/user/**").hasAuthority(UserRole.CLIENT.toString())
                .antMatchers("/rooms/**", "/reservation/**").hasAnyAuthority(UserRole.EMPLOYEE.toString(), UserRole.MANAGER.toString())
                .and().formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((httpServletRequest, httpServletResponse, authentication)
                        -> ((MyUserPrincipal) authentication.getPrincipal()).getAuthorities().parallelStream().forEach(authority -> {
                    try {
                        if (authority.getAuthority().equals(UserRole.CLIENT.toString())) {
                            httpServletResponse.sendRedirect("/user/home");
                        } else {
                            httpServletResponse.sendRedirect("/rooms/home");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }))
                .loginPage("/login")
                .and().rememberMe()
                .rememberMeParameter("rememberMe")
                .userDetailsService(customUserDetailsService)
                .key("keyfalopa")
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(60))
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and().exceptionHandling()
                .accessDeniedPage("/403")
                .and().csrf().disable();
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico", "/403");
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
