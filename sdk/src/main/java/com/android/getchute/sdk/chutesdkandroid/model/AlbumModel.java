/**
 Copyright(c)2011,Chute Corporation.All rights reserved.

 Redistribution and use in source and binary forms,with or without modification,
 are permitted provided that the following conditions are met:

 *Redistributions of source code must retain the above copyright notice,this
 list of conditions and the following disclaimer.
 *Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.
 *Neither the name of the Chute Corporation nor the names
 of its contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS"AS IS"AND
 ANY EXPRESS OR IMPLIED WARRANTIES,INCLUDING,BUT NOT LIMITED TO,THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 INDIRECT,INCIDENTAL,SPECIAL,EXEMPLARY,OR CONSEQUENTIAL DAMAGES(INCLUDING,
 BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;LOSS OF USE,
 DATA,OR PROFITS;OR BUSINESS INTERRUPTION)HOWEVER CAUSED AND ON ANY THEORY OF
 LIABILITY,WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT(INCLUDING NEGLIGENCE
 OR OTHERWISE)ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,EVEN IF ADVISED
 OF THE POSSIBILITY OF SUCH DAMAGE.
 **/
package com.android.getchute.sdk.chutesdkandroid.model;
/**
 * Albums represent a collection of assets which can be created by a single
 * user, viewed by everyone or privileged users.
 * <p>
 * Each album contains links, counters, shortcut, name, which user it belongs
 * to, media and comments moderators and time and date of creation.
 */
public class AlbumModel {

  public static final String TAG = AlbumModel.class.getSimpleName();
  /**
   * Unique identifier.
   */
  private String id;

  /**
   * Album links.
   */
  private LinkModel links;

  /**
   * Album counters.
   */
  private CounterModel counters;

  /**
   * Album Shortcut.
   */
  private String shortcut;

  /**
   * Album name.
   */
  private String name;

  /**
   * The user the album belongs to.
   */
  private UserModel user;

  /**
   * Time and date of creating the album.
   */
  private String createdAt;

  /**
   * Time and date of updating the album.
   */
  private String updatedAt;

  /**
   * Album description.
   */
  private String description;

  /**
   * Parent ID of the album.
   */
  private String parentId;

  /**
   * Number of images in the album
   */
  private int imagesCount;

  /**
   * Album's first asset
   */
  private AssetModel asset;

  /**
   * Flag indicating whether the album has new assets
   */
  private boolean hasNewAssets;

  /**
   * Getters and setters
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

  public CounterModel getCounters() {
    return counters;
  }

  public void setCounters(CounterModel counters) {
    this.counters = counters;
  }

  public String getShortcut() {
    return shortcut;
  }

  public void setShortcut(String shortcut) {
    this.shortcut = shortcut;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public int getImagesCount() {
    return imagesCount;
  }

  public void setImagesCount(int imagesCount) {
    this.imagesCount = imagesCount;
  }

  public AssetModel getAsset() {
    return asset;
  }

  public void setAsset(AssetModel asset) {
    this.asset = asset;
  }

  private void setCoverAsset(AssetModel coverAsset) {
    this.asset = coverAsset;
  }

  public boolean isHasNewAssets() {
    return hasNewAssets;
  }

  public void setHasNewAssets(boolean hasNewAssets) {
    this.hasNewAssets = hasNewAssets;
  }

  @Override public String toString() {
    return "AlbumModel{" +
        "id='" + id + '\'' +
        ", links=" + links +
        ", counters=" + counters +
        ", shortcut='" + shortcut + '\'' +
        ", name='" + name + '\'' +
        ", user=" + user +
        ", createdAt='" + createdAt + '\'' +
        ", updatedAt='" + updatedAt + '\'' +
        ", description='" + description + '\'' +
        ", parentId='" + parentId + '\'' +
        ", imagesCount=" + imagesCount +
        ", asset=" + asset +
        ", hasNewAssets=" + hasNewAssets +
        '}';
  }
}