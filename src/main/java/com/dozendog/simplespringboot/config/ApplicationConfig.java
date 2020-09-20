package com.dozendog.simplespringboot.config;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.dozendog.simplespringboot.util.LoggingInterceptor;





@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan(basePackages = {"com.dozendog.simplespringboot"})
@PropertySources({
        @PropertySource("classpath:application.properties")
})
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    public MappedInterceptor logInterceptor() {
        String[] pattern = new String[1];
        //define pattern path for logging
        pattern[0] = "/service/v1/**";
        return new MappedInterceptor(pattern, new LoggingInterceptor());
    }
    
  
}
