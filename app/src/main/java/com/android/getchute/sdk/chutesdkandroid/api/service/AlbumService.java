package com.android.getchute.sdk.chutesdkandroid.api.service;

import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumBodyRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface AlbumService {

  /** Observables **/

  @POST("albums") Observable<ResponseModel<AlbumModel>> createAlbumObservable(
      @Body AlbumBodyRequestModel albumBodyRequestModel);

  @DELETE("albums/{album_id}") Observable<ResponseModel<AlbumModel>> deleteAlbumObservable(
      @Path("album_id") String albumId);

  @GET("albums/{album_id}") Observable<ResponseModel<AlbumModel>> getAlbumObservable(
      @Path("album_id") String albumId);

  @GET("albums") Observable<ListResponseModel<AlbumModel>> listAlbumsObservable(
      @Query("per_page") String perPage);

  @GET("albums/{album_id}/albums")
  Observable<ListResponseModel<AlbumModel>> listNestedAlbumsObservable(
      @Path("album_id") String albumId);

  @PUT("albums/{id}") Observable<ResponseModel<AlbumModel>> updateAlbumObservable(
      @Path("id") String id,
      @Body AlbumBodyRequestModel albumBodyRequestModel);

  /** Calls **/

  @POST("albums") Call<ResponseModel<AlbumModel>> createAlbumCall(
      @Body AlbumBodyRequestModel albumBodyRequestModel);

  @DELETE("albums/{album_id}") Call<ResponseModel<AlbumModel>> deleteAlbumCall(
      @Path("album_id") String albumId);

  @GET("albums/{album_id}") Call<ResponseModel<AlbumModel>> getAlbumCall(
      @Path("album_id") String albumId);

  @GET("albums") Call<ListResponseModel<AlbumModel>> listAlbumsCall(
      @Query("per_page") String perPage);

  @GET("albums/{album_id}/albums") Call<ListResponseModel<AlbumModel>> listNestedAlbumsCall(
      @Path("album_id") String albumId);

  @PUT("albums/{id}") Call<ResponseModel<AlbumModel>> updateAlbumCall(@Path("id") String id,
      @Body AlbumBodyRequestModel albumBodyRequestModel);
}
