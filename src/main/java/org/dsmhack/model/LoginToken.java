/*
 * Here to Help
 * This is a restful web service used to log hours for non-profits to submit for money grants.
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.dsmhack.model;

import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class LoginToken {
  @Id
  @Column
  private String userGuid;

  @Column
  private String token;

  @Column
  private LocalDateTime tokenExpDate;

  public String getUserGuid() {
    return userGuid;
  }

  public LoginToken setUserGuid(String userGuid) {
    this.userGuid = userGuid;
    return this;
  }

  public String getToken() {
    return token;
  }

  public LoginToken setToken(String token) {
    this.token = token;
    return this;
  }

  public LocalDateTime getTokenExpDate() {
    return tokenExpDate;
  }

  public LoginToken setTokenExpDate(LocalDateTime tokenExpDate) {
    this.tokenExpDate = tokenExpDate;
    return this;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }
}