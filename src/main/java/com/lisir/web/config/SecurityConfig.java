package com.lisir.web.config;

import com.lisir.web.security.CustomerAuthenticationFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lxp
 * @Date 2021-05-06 19:00
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@ConfigurationProperties("auth")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private List<String> ignoreUrls;

    public List<String> getIgnoreUrls() {
        if(ignoreUrls == null){
            return new ArrayList<>();
        }
        return ignoreUrls;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers(getIgnoreUrls().toArray(new String[]{})).permitAll()
                .anyRequest().authenticated()
                .and().addFilter(new CustomerAuthenticationFilter(authenticationManager(), getIgnoreUrls()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

}
