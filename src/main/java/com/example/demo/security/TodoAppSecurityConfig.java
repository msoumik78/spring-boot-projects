package com.example.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class TodoAppSecurityConfig implements WebMvcConfigurer {

  private final JwtInterceptor jwtInterceptor;


  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry
      .addInterceptor(jwtInterceptor)
      .addPathPatterns("/api/1/tasks/**");
  }
}
