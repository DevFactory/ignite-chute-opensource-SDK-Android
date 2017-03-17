package com.android.getchute.sdk.chutesdkandroid.api.service;

import com.android.getchute.sdk.chutesdkandroid.model.VoteModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VoteService {

  /** Observables **/

  @GET("albums/{album_id}/assets/{asset_id}/votes")
  Observable<ResponseModel<VoteModel>> voteCountObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @DELETE("albums/{album_id}/assets/{asset_id}/votes") Observable<Void> unvoteObservable(
      @Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @POST("albums/{album_id}/assets/{asset_id}/votes")
  Observable<ResponseModel<VoteModel>> voteObservable(
      @Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  /** Calls **/

  @GET("albums/{album_id}/assets/{asset_id}/votes")
  Call<ResponseModel<VoteModel>> voteCountCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @DELETE("albums/{album_id}/assets/{asset_id}/votes") Call<Void> unvoteCall(
      @Path("album_id") String albumId,
      @Path("asset_id") String assetId);

  @POST("albums/{album_id}/assets/{asset_id}/votes") Call<ResponseModel<VoteModel>> voteCall(
      @Path("album_id") String albumId,
      @Path("asset_id") String assetId);
}
