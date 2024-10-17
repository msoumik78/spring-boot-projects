package com.example.demo.service;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private IUserDao iUserDao;

  private RSAKey rsaJWK;
    {
        try {
            rsaJWK = new RSAKeyGenerator(2048).keyID("123").generate();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private JWSSigner signer;
    {
        try {
            signer = new RSASSASigner(rsaJWK);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private UserService userService;

    @BeforeEach
  public void init(){
    userService = new UserService(iUserDao, rsaJWK, signer);
  }

  @Test
  void verify_hashed_password() {
    User user = new User();
    user.setUserName("username");
    user.setPassword("password");
    doNothing().when(iUserDao).createUser(isA(User.class));
    userService.createUser(user);
    assertTrue(BCrypt.checkpw("password", user.getPassword()));
  }

  @Test
  void check_claims_in_generated_jwt() throws Exception {
    User user = new User();
    user.setUserName("username");
    user.setPassword("password");
    String generatedJWT = userService.generateJWT(user);
    SignedJWT signedJwt = SignedJWT.parse(generatedJWT);
    JWTClaimsSet claimsSet = signedJwt.getJWTClaimsSet();
    System.out.println("Claim " + claimsSet.getClaim("userId"));
    assertEquals(claimsSet.getClaim("userName").toString(), "username");
  }
}
