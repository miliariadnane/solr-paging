package com.raminorujov.solrpaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by raminorujov on 23/06/2017.
 */
@SpringBootApplication
public class SolrPagingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolrPagingApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("PUT", "POST", "DELETE", "GET", "OPTIONS", "HEAD");
//            }
//        };
//    }
}
