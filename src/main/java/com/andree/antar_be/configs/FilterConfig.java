package com.andree.antar_be.configs;

import com.andree.antar_be.utils.JwtFilter;
import com.andree.antar_be.utils.JwtRefreshFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.refresh_secret}")
    private String refreshSecret;

    @Bean
    public FilterRegistrationBean jwtFilter(){
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter(secret));

        filter.addUrlPatterns("/api/v1/upload/private");
        return filter;
    }

    @Bean
    public FilterRegistrationBean jwtRefreshFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtRefreshFilter(refreshSecret));

        filter.addUrlPatterns("/api/v1/auth/refresh");
        return filter;
    }
}
