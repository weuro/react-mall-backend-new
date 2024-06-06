package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 这是spring mvc的跨域配置类，不是spring security的跨域配置类，security的跨域配置优先级更高，两者同时配置时以security的配置为准
@Configuration
public class CorsConfig implements WebMvcConfigurer {
     // 配置跨域请求
     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**") // 允许所有请求路径
                 .allowedOrigins("http://localhost:8080") // 允许该域名的跨域请求
//                 .allowedOriginPatterns("*") // 允许所有域名的跨域请求
                 .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许GET、POST、PUT、DELETE请求
                 .allowedHeaders("*") // 允许所有请求头
                 .allowCredentials(true) // 允许携带凭证
                 .maxAge(3600); // 预检请求的有效期，单位为秒
     }
}
