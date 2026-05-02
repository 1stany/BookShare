package org.projectwork.bookshare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<NoCacheIndexFilter> noCacheIndexFilter() {
        FilterRegistrationBean<NoCacheIndexFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new NoCacheIndexFilter());
        registration.addUrlPatterns("/*"); // intercetta tutto, ma filtra solo index.html
        registration.setOrder(1);
        return registration;
    }
}