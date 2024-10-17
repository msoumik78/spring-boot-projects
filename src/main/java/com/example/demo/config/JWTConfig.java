package com.example.demo.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

  @Bean
  public RSAKey generateKey() throws JOSEException {
    return new RSAKeyGenerator(2048)
      .keyID("123")
      .generate();
  }

  @Bean
  public JWSSigner generateSigner(RSAKey rsaJWK) throws JOSEException {
    return new RSASSASigner(rsaJWK);
  }

}
