package com.android.getchute.sdk.chutesdkandroid.api.heart;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.service.heart.HeartService;
import com.android.getchute.sdk.chutesdkandroid.model.HeartModel;
import com.android.getchute.sdk.chutesdkandroid.model.HeartModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;

public class MockHeartService implements HeartService {

  private final BehaviorDelegate<HeartService> delegate;

  public MockHeartService(BehaviorDelegate<HeartService> service) {
    this.delegate = service;
  }

  @Override public Observable<ResponseModel<HeartModel>> heartCountObservable(
      @Path("album_id") String albumId, @Path("asset_id") String assetId) {
    ResponseModel<HeartModel> response =
        HeartModelGenerator.getResponseModel(Constants.FilePaths.Heart.HEART_GET_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).heartCountObservable(albumId, assetId);
  }

  @Override
  public Observable<ResponseModel<Void>> unheartObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<Void> response =
        HeartModelGenerator.getHeartDeleteResponseModel(
            Constants.FilePaths.Heart.HEART_REMOVE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).unheartObservable(albumId, assetId);
  }

  @Override
  public Observable<ResponseModel<HeartModel>> heartObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<HeartModel> response =
        HeartModelGenerator.getResponseModel(Constants.FilePaths.Heart.HEART_POST_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).heartObservable(albumId, assetId);
  }

  @Override public Call<ResponseModel<HeartModel>> heartCountCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<HeartModel> response =
        HeartModelGenerator.getResponseModel(Constants.FilePaths.Heart.HEART_GET_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).heartCountCall(albumId, assetId);
  }

  @Override public Call<ResponseModel<Void>> unheartCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<Void> response =
        HeartModelGenerator.getHeartDeleteResponseModel(
            Constants.FilePaths.Heart.HEART_REMOVE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).unheartCall(albumId, assetId);
  }

  @Override public Call<ResponseModel<HeartModel>> heartCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<HeartModel> response =
        HeartModelGenerator.getResponseModel(Constants.FilePaths.Heart.HEART_POST_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).heartCall(albumId, assetId);
  }
}
