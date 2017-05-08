package com.android.getchute.sdk.chutesdkandroid.model;

import android.support.test.InstrumentationRegistry;
import android.util.Log;
import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.FileUtil;
import com.android.getchute.sdk.chutesdkandroid.api.RetrofitTestService;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserRequestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ModelGenerator {

  private static final String TAG = ModelGenerator.class.getSimpleName();
  private static Gson gson = RetrofitTestService.get().getGson();

  public static ResponseModel<Void> getEmptyResponseModel(String filePath) {
    ResponseModel<Void> response = new ResponseModel<>();
    try {
      String jsonString = FileUtil.getStringFromFile(
          InstrumentationRegistry.getContext(), filePath);
      Type type = new TypeToken<ResponseModel<Void>>() {}.getType();
      response =
          gson.fromJson(jsonString, type);
    } catch (Exception e) {
      Log.e(TAG, "Failed to create ResponseModel<Void>: " + e.getMessage());
    }
    return response;
  }

  public static class Login {

    public static LoginRequestModel getRequestModel() throws Exception {
      return gson.fromJson(
          FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
              Constants.FilePaths.Login.REQUEST_SUCCESS), LoginRequestModel.class);
    }

    public static LoginRequestModel getFailedRequestModel() throws Exception {
      return gson.fromJson(
          FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
              Constants.FilePaths.Login.REQUEST_FAIL), LoginRequestModel.class);
    }

    public static LoginResponseModel getResponseModel() {
      LoginResponseModel loginResponseModel = new LoginResponseModel();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), Constants.FilePaths.Login.RESPONSE_SUCCESS);
        JSONObject json = new JSONObject(jsonString);
        loginResponseModel = gson.fromJson(json.toString(), LoginResponseModel.class);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create LoginResponseModel: " + e.getMessage());
      }
      return loginResponseModel;
    }

    public static LoginResponseModel getFailedResponseModel() {
      LoginResponseModel loginResponseModel = new LoginResponseModel();
      try {
        loginResponseModel = gson.fromJson(FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), Constants.FilePaths.Login.RESPONSE_FAIL),
            LoginResponseModel.class);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create LoginResponseModel: " + e.getMessage());
      }
      return loginResponseModel;
    }
  }

  public static class Album {

    /** Request **/
    public static AlbumRequestModel getRequestModel(String fileName) throws Exception {
      return gson.fromJson(
          FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
              fileName), AlbumRequestModel.class);
    }

    public static AlbumRequestModel getFailedRequestModel(String fileName) throws Exception {
      return gson.fromJson(
          FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
              fileName), AlbumRequestModel.class);
    }

    /** List Response **/
    public static ListResponseModel<AlbumModel> getListResponseModel(String fileName) {
      ListResponseModel<AlbumModel> albumModelListResponseModel = new ListResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ListResponseModel<AlbumModel>>() {}.getType();
        albumModelListResponseModel = gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ListResponseModel<AlbumModel>: " + e.getMessage());
      }
      return albumModelListResponseModel;
    }

    public static ListResponseModel<AlbumModel> getFailedListResponseModel(String fileName) {
      ListResponseModel<AlbumModel> albumModelListResponseModel =
          new ListResponseModel<>();
      try {
        Type type = new TypeToken<ListResponseModel<AlbumModel>>() {}.getType();
        albumModelListResponseModel = gson.fromJson(FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName),
            type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ListResponseModel<AlbumModel>: " + e.getMessage());
      }
      return albumModelListResponseModel;
    }

    /** Response **/
    public static ResponseModel<AlbumModel> getResponseModel(String fileName) {
      ResponseModel<AlbumModel> response = new ResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ResponseModel<AlbumModel>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<AlbumModel>: " + e.getMessage());
      }
      return response;
    }

    public static ResponseModel<AlbumModel> getFailedResponseModel(String fileName) {
      ResponseModel<AlbumModel> responseModel =
          new ResponseModel<>();
      try {
        Type type = new TypeToken<ResponseModel<AlbumModel>>() {}.getType();
        responseModel = gson.fromJson(FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName),
            type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<AlbumModel>: " + e.getMessage());
      }
      return responseModel;
    }
  }

  public static class Asset {

    /** List Response **/
    public static ListResponseModel<AssetModel> getListResponseModel(String fileName) {
      ListResponseModel<AssetModel> assetModelListResponseModel = new ListResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ListResponseModel<AssetModel>>() {}.getType();
        assetModelListResponseModel = gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ListResponseModel<AssetModel>: " + e.getMessage());
      }
      return assetModelListResponseModel;
    }

    /** Response **/
    public static ResponseModel<AssetModel> getResponseModel(String fileName) {
      ResponseModel<AssetModel> response = new ResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ResponseModel<AssetModel>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<AlbumModel>: " + e.getMessage());
      }
      return response;
    }

    /** Exif response **/
    public static ResponseModel<HashMap<String, String>> getExifResponseModel(String filePath) {
      ResponseModel<HashMap<String, String>> responseModel =
          new ResponseModel<>();
      try {
        Type type = new TypeToken<ResponseModel<HashMap<String, String>>>() {}.getType();
        responseModel = gson.fromJson(FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), filePath),
            type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<Hashmap<String, String>>: " + e.getMessage());
      }
      return responseModel;
    }
  }

  public static class Heart {

    /** Response **/
    public static ResponseModel<HeartModel> getResponseModel(String fileName) {
      ResponseModel<HeartModel> response = new ResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ResponseModel<HeartModel>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<HeartModel>: " + e.getMessage());
      }
      return response;
    }
  }

  public static class Tag {

    /** Response **/
    public static ListResponseModel<String> getListResponseModel(String fileName) {
      ListResponseModel<String> response = new ListResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ListResponseModel<String>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ListResponseModel<String>: " + e.getMessage());
      }
      return response;
    }

    public static Map<String, List<String>> getRequestBody() {
      Map<String, List<String>> response = new HashMap<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), Constants.FilePaths.Tag.TAG_POST_REQUEST_BODY);
        Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create Map<String, List<String>>: " + e.getMessage());
      }
      return response;
    }
  }

  public static class User {

    /** Request **/
    public static UserRequestModel getRequestModel(String fileName) throws Exception {
      return gson.fromJson(
          FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
              fileName), UserRequestModel.class);
    }

    public static UserRequestModel getFailedRequestModel(String fileName) throws Exception {
      return gson.fromJson(
          FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
              fileName), UserRequestModel.class);
    }

    /** Response **/
    public static ResponseModel<UserModel> getResponseModel(String fileName) {
      ResponseModel<UserModel> response = new ResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ResponseModel<UserModel>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<UserModel>: " + e.getMessage());
      }
      return response;
    }
  }

  public static class Vote {

    /** Response **/
    public static ResponseModel<VoteModel> getResponseModel(String fileName) {
      ResponseModel<VoteModel> response = new ResponseModel<>();
      try {
        String jsonString = FileUtil.getStringFromFile(
            InstrumentationRegistry.getContext(), fileName);
        Type type = new TypeToken<ResponseModel<VoteModel>>() {}.getType();
        response =
            gson.fromJson(jsonString, type);
      } catch (Exception e) {
        Log.e(TAG, "Failed to create ResponseModel<HeartModel>: " + e.getMessage());
      }
      return response;
    }
  }
}
