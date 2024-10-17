package com.example.demo.service;

import com.example.demo.dao.IUserDao;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.model.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
  private final IUserDao iUserDao;
  private final RSAKey rsaJWK;
  private final JWSSigner signer;

  @Value("${jwt-expiry-in-seconds}")
  private int jwtExpiryTime;

  public void createUser(User user) {
    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(hashedPassword);
    iUserDao.createUser(user);
  }

  public User getUserDetailsFromCredentials(String userName, String password) {
    User user;
    try {
       user = iUserDao.getUserDetails(userName);
    } catch (EmptyResultDataAccessException e) {
      throw new InvalidInputException("Invalid userName : "+userName);
    }
    if (BCrypt.checkpw(password, user.getPassword())){
      return user;
    } else {
      throw new PasswordMismatchException("Password does not match for userName : "+userName);
    }
  }

  public String generateJWT(User user) throws JOSEException {
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
      .issuer("https://c2id.com")
      .claim("userId", user.getUserId())
      .claim("userName", user.getUserName())
      .expirationTime(new Date(new Date().getTime() + jwtExpiryTime * 1000))
      .build();

    SignedJWT signedJWT = new SignedJWT(
      new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
      claimsSet);
    signedJWT.sign(signer);
    return signedJWT.serialize();
  }

  public boolean validateJWT(String jwtAsString) throws ParseException, JOSEException {
    SignedJWT parsedJWT = SignedJWT.parse(jwtAsString);
    RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();
    JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);
    if (!parsedJWT.verify(verifier)) {
      return false;
    }
    Date certificateExpiryTime = parsedJWT.getJWTClaimsSet().getExpirationTime();
    Date currentTime = new Date();
    return !currentTime.after(certificateExpiryTime);
  }


}
