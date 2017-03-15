/**
 Copyright (c) 2011, Chute Corporation. All rights reserved.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.
 * Neither the name of the  Chute Corporation nor the names
 of its contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 OF THE POSSIBILITY OF SUCH DAMAGE.
 **/
package com.android.getchute.sdk.chutesdkandroid.api.service;

import com.android.getchute.sdk.chutesdkandroid.model.HeartModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface HeartService {

  /** Observables **/

  @GET("albums/{album_id}/assets/{asset_id}/hearts")
  Observable<ResponseModel<HeartModel>> heartCountObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @DELETE("albums/{album_id}/assets/{asset_id}/hearts")
  Observable<ResponseModel<HeartModel>> unheartObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @POST("albums/{album_id}/assets/{asset_id}/hearts")
  Observable<ResponseModel<HeartModel>> heartObservable(
      @Path("album_id") String albumId, @Path("asset_id") String assetId);

  /** Calls **/

  @GET("albums/{album_id}/assets/{asset_id}/hearts")
  Call<ResponseModel<HeartModel>> heartCountCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @DELETE("albums/{album_id}/assets/{asset_id}/hearts")
  Call<ResponseModel<HeartModel>> unheartCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @POST("albums/{album_id}/assets/{asset_id}/hearts") Call<ResponseModel<HeartModel>> heartCall(
      @Path("album_id") String albumId, @Path("asset_id") String assetId);
}
