package com.example.demo.security;

import com.example.demo.service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

  private final UserService userService;
  @Override
  public boolean preHandle(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final Object handler) throws IOException, ParseException, JOSEException {
        String receivedJWT = request.getHeader("x-jwt-user");
        System.out.println("Received jwt : "+receivedJWT);
        if ((receivedJWT == null) || (receivedJWT.isEmpty())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        if (userService.validateJWT(receivedJWT)) {
          return true;
        } else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return false;
        }
  }
}
