package com.android.getchute.sdk.chutesdkandroid.model;

import android.text.TextUtils;
import com.android.getchute.sdk.chutesdkandroid.model.body.ProfileRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserRequestModel;
import java.util.ArrayList;
import java.util.List;

public class ModelBluePrint {

  public static LinkModel createLinkModel(LinkInfoModel self, LinkInfoModel assets,
      LinkInfoModel geo, LinkInfoModel exif, LinkInfoModel heart, LinkInfoModel vote) {
    LinkModel linkModel = new LinkModel();
    if (self != null) { linkModel.setSelf(self); }
    if (assets != null) { linkModel.setAssets(assets); }
    if (geo != null) { linkModel.setGeo(geo); }
    if (exif != null) { linkModel.setExif(exif); }
    if (heart != null) { linkModel.setHeart(heart); }
    if (vote != null) { linkModel.setVote(vote); }
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

  public static AssetModel createAssetModel(String id, LinkModel linkModel, String createdAt,
      String updatedAt, String shortcut, String type, String chuteAssetId, List<String> tags,
      int hearts, int votes, String thumbnail, String url, UserModel userModel) {
    AssetModel assetModel = new AssetModel();
    assetModel.setId(id);
    if (linkModel != null) { assetModel.setLinks(linkModel); }
    assetModel.setCreatedAt(createdAt);
    assetModel.setUpdatedAt(updatedAt);
    assetModel.setShortcut(shortcut);
    assetModel.setType(type);
    assetModel.setHearts(hearts);
    assetModel.setVotes(votes);
    assetModel.setThumbnail(thumbnail);
    assetModel.setUrl(url);
    assetModel.setTags((ArrayList<String>) tags);
    assetModel.setChuteAssetId(chuteAssetId);
    if (userModel != null) { assetModel.setUser(userModel); }
    return assetModel;
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

  public static UserRequestModel createUserRequestModel(String email, String name, String username,
      String company,
      String title, String password, String confirmPassword) {
    UserRequestModel userRequestModel = new UserRequestModel();
    userRequestModel.setEmail(email);
    userRequestModel.setName(name);
    userRequestModel.setUsername(username);
    ProfileRequestModel profileRequestModel = new ProfileRequestModel();
    profileRequestModel.setCompany(company);
    profileRequestModel.setTitle(title);
    userRequestModel.setProfile(profileRequestModel);
    userRequestModel.setPassword(password);
    userRequestModel.setConfirmPassword(confirmPassword);
    return userRequestModel;
  }
}
