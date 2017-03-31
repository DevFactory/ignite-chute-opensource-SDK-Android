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
package com.android.getchute.sdk.chutesdkandroid.api.service.asset;

import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AssetRequestModel;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface AssetService {

  /** Observables */

  @GET("albums/{album_id}/assets/{asset_id}") Observable<ResponseModel<AssetModel>> getObservable(
      @Path("album_id") String albumId, @Path("asset_id") String assetId);

  @GET("albums/{id}/assets") Observable<ListResponseModel<AssetModel>> listObservable(
      @Path("id") String id, @Query("per_page") String perPage, @Query("type") String type,
      @Query("username") String username, @Query("tags") List<String> tags,
      @Query("service") String service);

  @GET Observable<ListResponseModel<AssetModel>> getNextPageObservable(@Url String url);

  @DELETE("albums/{album_id}/assets/{asset_id}")
  Observable<ResponseModel<Void>> deleteObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @POST("albums/{album_id}/assets/{asset_id}/copy/{new_album_id}")
  Observable<ResponseModel<AssetModel>> copyObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId);

  @POST("albums/{album_id}/assets/{asset_id}/move/{new_album_id}")
  Observable<ResponseModel<AssetModel>> moveObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId);

  @POST("albums/{album_id}/assets/import")
  Observable<ListResponseModel<AssetModel>> importObservable(@Path("album_id") String albumId,
      @Body AssetRequestModel body);

  @GET("albums/{album_id}/assets/{asset_id}/exif")
  Observable<ResponseModel<HashMap<String, String>>> exifObservable(
      @Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  /** Calls */

  @GET("albums/{album_id}/assets/{asset_id}") Call<ResponseModel<AssetModel>> getCall(
      @Path("album_id") String albumId, @Path("asset_id") String assetId);

  @GET("albums/{id}/assets") Call<ListResponseModel<AssetModel>> listCall(
      @Path("id") String id, @Query("per_page") String perPage, @Query("type") String type,
      @Query("username") String username, @Query("tags") List<String> tags,
      @Query("service") String service);

  @GET Call<ListResponseModel<AssetModel>> getNextPageCall(@Url String url);

  @DELETE("albums/{album_id}/assets/{asset_id}")
  Call<ResponseModel<Void>> deleteCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @POST("albums/{album_id}/assets/{asset_id}/copy/{new_album_id}")
  Call<ResponseModel<AssetModel>> copyCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId);

  @POST("albums/{album_id}/assets/{asset_id}/move/{new_album_id}")
  Call<ResponseModel<AssetModel>> moveCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId);

  @POST("albums/{album_id}/assets/import")
  Call<ListResponseModel<AssetModel>> importCall(@Path("album_id") String albumId,
      @Body AssetRequestModel body);

  @GET("albums/{album_id}/assets/{asset_id}/exif")
  Call<ResponseModel<HashMap<String, String>>> exifCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);
}
