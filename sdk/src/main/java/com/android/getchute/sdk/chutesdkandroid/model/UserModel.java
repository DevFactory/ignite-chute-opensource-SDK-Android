/**
 * Copyright (c) 2011, Chute Corporation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the Chute Corporation nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 **/
package com.android.getchute.sdk.chutesdkandroid.model;

import java.io.Serializable;

/**
 * The {@link UserModel} class contains user features such as: links, name,
 * username and avatar URL.
 */
public class UserModel implements Serializable {

  /**
   * Unique identifier.
   */
  private String id;

  /**
   * User links.
   */
  private LinkModel links;

  /**
   * Name of the user.
   */
  private String name;

  /**
   * Username of the user.
   */
  private String username;

  /**
   * User avatar URL.
   */
  private String avatar;

  /**
   * Time and date of creation.
   */
  private String createdAt;

  /**
   * Time and date of update.
   */
  private String updatedAt;

  /**
   * User e-mail.
   */
  private String email;

  /**
   * OAuth token.
   */
  private String oauthToken;

  /**
   * User status. It can be pending or verified.
   */
  private String status;

  private ProfileModel profileModel;
  private String lastLogin;
  private boolean admin;
  private OauthAppModel oauthApp;

  /**
   * Getters and setters.
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LinkModel getLinks() {
    return links;
  }

  public void setLinks(LinkModel links) {
    this.links = links;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOauthToken() {
    return oauthToken;
  }

  public void setOauthToken(String oauthToken) {
    this.oauthToken = oauthToken;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ProfileModel getProfileModel() {
    return profileModel;
  }

  public void setProfileModel(ProfileModel profileModel) {
    this.profileModel = profileModel;
  }

  public String getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(String lastLogin) {
    this.lastLogin = lastLogin;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public OauthAppModel getOauthApp() {
    return oauthApp;
  }

  public void setOauthApp(OauthAppModel oauthApp) {
    this.oauthApp = oauthApp;
  }

  @Override public String toString() {
    return getClass().getSimpleName() + "[" +
        "id=" + id + ", " +
        "links=" + links + ", " +
        "name=" + name + ", " +
        "username=" + username + ", " +
        "avatar=" + avatar + ", " +
        "createdAt=" + createdAt + ", " +
        "updatedAt=" + updatedAt + ", " +
        "email=" + email + ", " +
        "oauthToken=" + oauthToken + ", " +
        "status=" + status + ", " +
        "profileModel=" + profileModel + ", " +
        "lastLogin=" + lastLogin + ", " +
        "admin=" + admin + ", " +
        "oauthApp=" + oauthApp + "]";
  }
}