/**
 * Copyright (c) 2011, Chute Corporation. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * <p>
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the Chute Corporation nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * <p>
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
package com.android.getchute.sdk.chutesdkandroid.api.service.album;

import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumRequestModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface AlbumService {

  /** Observables **/

  @POST("albums") Observable<ResponseModel<AlbumModel>> createAlbumObservable(
      @Body AlbumRequestModel albumRequestModel);

  @DELETE("albums/{album_id}") Observable<ResponseModel<Void>> deleteAlbumObservable(
      @Path("album_id") String albumId);

  @GET("albums/{album_id}") Observable<ResponseModel<AlbumModel>> getAlbumObservable(
      @Path("album_id") String albumId);

  @GET("albums") Observable<ListResponseModel<AlbumModel>> listAlbumsObservable(
      @Query("per_page") String perPage);

  @GET("albums/{album_id}/albums")
  Observable<ListResponseModel<AlbumModel>> listNestedAlbumsObservable(
      @Path("album_id") String albumId, @Query("per_page") String perPage);

  @PUT("albums/{id}") Observable<ResponseModel<AlbumModel>> updateAlbumObservable(
      @Path("id") String id,
      @Body AlbumRequestModel albumRequestModel);

  @GET Observable<ListResponseModel<AlbumModel>> getNextPageObservable(@Url String url);

  /** Calls **/

  @POST("albums") Call<ResponseModel<AlbumModel>> createAlbumCall(
      @Body AlbumRequestModel albumRequestModel);

  @DELETE("albums/{album_id}") Call<ResponseModel<Void>> deleteAlbumCall(
      @Path("album_id") String albumId);

  @GET("albums/{album_id}") Call<ResponseModel<AlbumModel>> getAlbumCall(
      @Path("album_id") String albumId);

  @GET("albums") Call<ListResponseModel<AlbumModel>> listAlbumsCall(
      @Query("per_page") String perPage);

  @GET("albums/{album_id}/albums") Call<ListResponseModel<AlbumModel>> listNestedAlbumsCall(
      @Path("album_id") String albumId, @Query("per_page") String perPage);

  @PUT("albums/{id}") Call<ResponseModel<AlbumModel>> updateAlbumCall(@Path("id") String id,
      @Body AlbumRequestModel albumRequestModel);

  @GET Call<ListResponseModel<AlbumModel>> getNextPageCall(@Url String url);
}
