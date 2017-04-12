package com.android.getchute.sdk.chutesdkandroid.model;

import android.text.TextUtils;

public class ModelBluePrint {

  public static LinkModel createLinkModel(LinkInfoModel self, LinkInfoModel assets) {
    LinkModel linkModel = new LinkModel();
    if (self != null) { linkModel.setSelf(self); }
    if (assets != null) { linkModel.setAssets(assets); }
    return linkModel;
  }

  public static LinkInfoModel createLinkInfoModel(String href, String title) {
    LinkInfoModel linkInfoModel = new LinkInfoModel();
    linkInfoModel.setHref(href);
    linkInfoModel.setTitle(title);
    return linkInfoModel;
  }

  public static UserModel createUserModel(String id, String createdAt, String updatedAt,
      String name,
      String username, String avatar, String lastLogin, boolean admin) {
    UserModel userModel = new UserModel();
    userModel.setId(id);
    userModel.setCreatedAt(createdAt);
    userModel.setUpdatedAt(updatedAt);
    if (!TextUtils.isEmpty(name)) { userModel.setName(name); }
    if (!TextUtils.isEmpty(username)) { userModel.setUsername(username); }
    if (!TextUtils.isEmpty(avatar)) { userModel.setAvatar(avatar); }
    if (!TextUtils.isEmpty(lastLogin)) { userModel.setLastLogin(lastLogin); }
    userModel.setAdmin(admin);
    return userModel;
  }

  public static AlbumModel createAlbumModel(String id, LinkModel linkModel, String createdAt,
      String updatedAt, String shortcut, String name, boolean hasNewAssets, UserModel userModel) {
    AlbumModel albumModel = new AlbumModel();
    albumModel.setId(id);
    if (linkModel != null) { albumModel.setLinks(linkModel); }
    albumModel.setCreatedAt(createdAt);
    albumModel.setUpdatedAt(updatedAt);
    albumModel.setShortcut(shortcut);
    albumModel.setName(name);
    albumModel.setHasNewAssets(hasNewAssets);
    if (userModel != null) { albumModel.setUser(userModel); }
    return albumModel;
  }

  public static ResponseStatusModel createResponseStatusModel(String error, int code, int version,
      String href, String title) {
    ResponseStatusModel responseStatusModel = new ResponseStatusModel();
    if (!TextUtils.isEmpty(error)) { responseStatusModel.setError(error); }
    responseStatusModel.setCode(code);
    responseStatusModel.setVersion(version);
    responseStatusModel.setHref(href);
    if (!TextUtils.isEmpty(title)) { responseStatusModel.setTitle(title); }
    return responseStatusModel;
  }
}
