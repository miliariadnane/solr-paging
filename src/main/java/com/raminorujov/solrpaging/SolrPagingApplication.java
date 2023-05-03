package com.raminorujov.solrpaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Updated By miliariadnane on 03/05/2023
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
