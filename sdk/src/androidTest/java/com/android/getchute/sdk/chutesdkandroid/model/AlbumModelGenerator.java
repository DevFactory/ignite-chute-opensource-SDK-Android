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
 * Neither the name of the  Chute Corporation nor the names
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

import android.support.test.InstrumentationRegistry;
import android.util.Log;
import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.FileUtil;
import com.android.getchute.sdk.chutesdkandroid.api.RetrofitTestService;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumRequestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AlbumModelGenerator {

  private static final String TAG = AlbumModelGenerator.class.getSimpleName();
  private static Gson gson = RetrofitTestService.get().getGson();

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

  /** Delete album response **/
  public static ResponseModel<Void> getAlbumDeleteResponseModel() {
    ResponseModel<Void> response = new ResponseModel<>();
    try {
      String jsonString = FileUtil.getStringFromFile(
          InstrumentationRegistry.getContext(), Constants.FilePaths.Album.DELETE_RESPONSE_SUCCESS);
      Type type = new TypeToken<ResponseModel<Void>>() {}.getType();
      response =
          gson.fromJson(jsonString, type);
    } catch (Exception e) {
      Log.e(TAG, "Failed to create ResponseModel<Void>: " + e.getMessage());
    }
    return response;
  }

  public static ResponseModel<Void> getAlbumDeleteFailedResponseModel() {
    ResponseModel<Void> responseModel =
        new ResponseModel<>();
    try {
      Type type = new TypeToken<ResponseModel<Void>>() {}.getType();
      responseModel = gson.fromJson(FileUtil.getStringFromFile(
          InstrumentationRegistry.getContext(), Constants.FilePaths.Album.DELETE_RESPONSE_FAIL_NONEXISTENT_ALBUM),
          type);
    } catch (Exception e) {
      Log.e(TAG, "Failed to create ResponseModel<Void>: " + e.getMessage());
    }
    return responseModel;
  }

}
