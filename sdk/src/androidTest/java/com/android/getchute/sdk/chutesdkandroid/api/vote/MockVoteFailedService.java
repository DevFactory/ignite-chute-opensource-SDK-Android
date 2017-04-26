package com.android.getchute.sdk.chutesdkandroid.api.vote;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.service.vote.VoteService;
import com.android.getchute.sdk.chutesdkandroid.model.VoteModel;
import com.android.getchute.sdk.chutesdkandroid.model.VoteModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;

public class MockVoteFailedService implements VoteService {

  private final BehaviorDelegate<VoteService> delegate;

  public MockVoteFailedService(BehaviorDelegate<VoteService> service) {
    this.delegate = service;
  }

  @Override
  public Observable<ResponseModel<VoteModel>> voteCountObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<VoteModel> response =
        VoteModelGenerator.getResponseModel(
            Constants.FilePaths.Vote.VOTE_GET_RESPONSE_FAIL_ASSET_NOT_FOUND);
    return delegate.returningResponse(response).voteCountObservable(albumId, assetId);
  }

  @Override
  public Observable<ResponseModel<Void>> unvoteObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<Void> response =
        VoteModelGenerator.getVoteDeleteResponseModel(
            Constants.FilePaths.Vote.VOTE_REMOVE_RESPONSE_FAIL);
    return delegate.returningResponse(response).unvoteObservable(albumId, assetId);
  }

  @Override
  public Observable<ResponseModel<VoteModel>> voteObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<VoteModel> response =
        VoteModelGenerator.getResponseModel(
            Constants.FilePaths.Vote.VOTE_POST_RESPONSE_FAIL_ASSET_NOT_FOUND);
    return delegate.returningResponse(response).voteObservable(albumId, assetId);
  }

  @Override public Call<ResponseModel<VoteModel>> voteCountCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<VoteModel> response =
        VoteModelGenerator.getResponseModel(
            Constants.FilePaths.Vote.VOTE_GET_RESPONSE_FAIL_ASSET_NOT_FOUND);
    return delegate.returningResponse(response).voteCountCall(albumId, assetId);
  }

  @Override public Call<ResponseModel<Void>> unvoteCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<Void> response =
        VoteModelGenerator.getVoteDeleteResponseModel(
            Constants.FilePaths.Vote.VOTE_REMOVE_RESPONSE_FAIL);
    return delegate.returningResponse(response).unvoteCall(albumId, assetId);
  }

  @Override public Call<ResponseModel<VoteModel>> voteCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<VoteModel> response =
        VoteModelGenerator.getResponseModel(
            Constants.FilePaths.Vote.VOTE_POST_RESPONSE_FAIL_ASSET_NOT_FOUND);
    return delegate.returningResponse(response).voteCall(albumId, assetId);
  }
}
