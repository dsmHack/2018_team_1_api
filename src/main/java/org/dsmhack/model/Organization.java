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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Organization {
  @Id
  @Column
  private String orgGuid;

  @NotNull
  @Size(min = 1, max = 50)
  @Column
  private String name;

  @NotNull
  @Size(min = 1, max = 250)
  @Column
  private String description;

  @Column
  private String email;

  @Column
  private String phoneNumber;

  @NotNull
  @Size(min = 1, max = 100)
  @Column
  private String websiteUrl;

  @Column
  private String facebookUrl;

  @Column
  private String twitterUrl;

  @Column
  private String instagramUrl;

  @Column
  private String address1;

  @Column
  private String city;

  @Column
  private String state;

  @Column
  private String zip;

  public String getOrgGuid() {
    return orgGuid;
  }

  public Organization setOrgGuid(String orgGuid) {
    this.orgGuid = orgGuid;
    return this;
  }

  public String getName() {
    return name;
  }

  public Organization setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Organization setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Organization setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Organization setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public Organization setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
    return this;
  }

  public String getFacebookUrl() {
    return facebookUrl;
  }

  public Organization setFacebookUrl(String facebookUrl) {
    this.facebookUrl = facebookUrl;
    return this;
  }

  public String getTwitterUrl() {
    return twitterUrl;
  }

  public Organization setTwitterUrl(String twitterUrl) {
    this.twitterUrl = twitterUrl;
    return this;
  }

  public String getInstagramUrl() {
    return instagramUrl;
  }

  public Organization setInstagramUrl(String instagramUrl) {
    this.instagramUrl = instagramUrl;
    return this;
  }

  public String getAddress1() {
    return address1;
  }

  public Organization setAddress1(String address1) {
    this.address1 = address1;
    return this;
  }

  public String getCity() {
    return city;
  }

  public Organization setCity(String city) {
    this.city = city;
    return this;
  }

  public String getState() {
    return state;
  }

  public Organization setState(String state) {
    this.state = state;
    return this;
  }

  public String getZip() {
    return zip;
  }

  public Organization setZip(String zip) {
    this.zip = zip;
    return this;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }
}

