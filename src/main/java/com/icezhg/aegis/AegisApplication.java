package com.icezhg.aegis;

import java.util.Collections;

import com.icezhg.aegis.filter.CORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by zhongjibing on 2017/8/16.
 */
@SpringBootApplication
public class AegisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AegisApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CORSFilter());
        registrationBean.setName("CORSFilter2");
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }
}
