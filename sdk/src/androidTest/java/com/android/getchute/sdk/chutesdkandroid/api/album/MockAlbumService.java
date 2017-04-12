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
package com.android.getchute.sdk.chutesdkandroid.api.album;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.service.album.AlbumService;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumRequestModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;

public class MockAlbumService implements AlbumService {

  private final BehaviorDelegate<AlbumService> delegate;

  public MockAlbumService(BehaviorDelegate<AlbumService> service) {
    this.delegate = service;
  }

  @Override public Observable<ResponseModel<AlbumModel>> createAlbumObservable(
      @Body AlbumRequestModel albumRequestModel) {
    ResponseModel<AlbumModel> response =
        AlbumModelGenerator.getResponseModel(Constants.FilePaths.Album.CREATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).createAlbumObservable(albumRequestModel);
  }

  @Override
  public Observable<ResponseModel<Void>> deleteAlbumObservable(@Path("album_id") String albumId) {
    ResponseModel<Void> response = AlbumModelGenerator.getAlbumDeleteResponseModel();
    return delegate.returningResponse(response).deleteAlbumObservable(albumId);
  }

  @Override public Observable<ResponseModel<AlbumModel>> getAlbumObservable(
      @Path("album_id") String albumId) {
    ResponseModel<AlbumModel> response =
        AlbumModelGenerator.getResponseModel(Constants.FilePaths.Album.GET_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getAlbumObservable(albumId);
  }

  @Override public Observable<ListResponseModel<AlbumModel>> listAlbumsObservable(
      @Query("per_page") String perPage) {
    ListResponseModel<AlbumModel> response =
        AlbumModelGenerator.getListResponseModel(Constants.FilePaths.Album.LIST_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).listAlbumsObservable(perPage);
  }

  @Override public Observable<ListResponseModel<AlbumModel>> listNestedAlbumsObservable(
      @Path("album_id") String albumId, @Query("per_page") String perPage) {
    ListResponseModel<AlbumModel> response =
        AlbumModelGenerator.getListResponseModel(Constants.FilePaths.Album.NESTED_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).listNestedAlbumsObservable(albumId, perPage);
  }

  @Override
  public Observable<ResponseModel<AlbumModel>> updateAlbumObservable(@Path("id") String id,
      @Body AlbumRequestModel albumRequestModel) {
    ResponseModel<AlbumModel> response =
        AlbumModelGenerator.getResponseModel(Constants.FilePaths.Album.UPDATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).createAlbumObservable(albumRequestModel);
  }

  @Override public Call<ResponseModel<AlbumModel>> createAlbumCall(
      @Body AlbumRequestModel albumRequestModel) {
    ResponseModel<AlbumModel> response =
        AlbumModelGenerator.getResponseModel(Constants.FilePaths.Album.CREATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).createAlbumCall(albumRequestModel);
  }

  @Override public Call<ResponseModel<Void>> deleteAlbumCall(@Path("album_id") String albumId) {
    ResponseModel<Void> response = AlbumModelGenerator.getAlbumDeleteResponseModel();
    return delegate.returningResponse(response).deleteAlbumCall(albumId);
  }

  @Override public Call<ResponseModel<AlbumModel>> getAlbumCall(@Path("album_id") String albumId) {
    ResponseModel<AlbumModel> response =
        AlbumModelGenerator.getResponseModel(Constants.FilePaths.Album.GET_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getAlbumCall(albumId);
  }

  @Override
  public Call<ListResponseModel<AlbumModel>> listAlbumsCall(@Query("per_page") String perPage) {
    ListResponseModel<AlbumModel> response =
        AlbumModelGenerator.getListResponseModel(Constants.FilePaths.Album.LIST_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).listAlbumsCall(perPage);
  }

  @Override
  public Call<ListResponseModel<AlbumModel>> listNestedAlbumsCall(@Path("album_id") String albumId,
      @Query("per_page") String perPage) {
    ListResponseModel<AlbumModel> response =
        AlbumModelGenerator.getListResponseModel(Constants.FilePaths.Album.NESTED_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).listNestedAlbumsCall(albumId, perPage);
  }

  @Override public Call<ResponseModel<AlbumModel>> updateAlbumCall(@Path("id") String id,
      @Body AlbumRequestModel albumRequestModel) {
    ResponseModel<AlbumModel> response =
        AlbumModelGenerator.getResponseModel(Constants.FilePaths.Album.UPDATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).createAlbumCall(albumRequestModel);
  }
}
