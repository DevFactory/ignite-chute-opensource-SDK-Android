package com.android.getchute.sdk.chutesdkandroid.api.user;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.service.user.UserService;
import com.android.getchute.sdk.chutesdkandroid.model.ModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserRequestModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;

public class MockUserService implements UserService {

  private final BehaviorDelegate<UserService> delegate;

  public MockUserService(BehaviorDelegate<UserService> service) {
    this.delegate = service;
  }

  @Override
  public Observable<ResponseModel<UserModel>> createUserObservable(@Body UserRequestModel body) {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(Constants.FilePaths.User.USER_CREATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).createUserObservable(body);
  }

  @Override public Observable<ResponseModel<UserModel>> getUserObservable(@Path("id") String id) {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(Constants.FilePaths.User.USER_GET_RESPONSE_OK);
    return delegate.returningResponse(response).getUserObservable(id);
  }

  @Override public Observable<ResponseModel<UserModel>> getCurrentUserObservable() {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(
            Constants.FilePaths.User.USER_CURRENT_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getCurrentUserObservable();
  }

  @Override public Observable<ResponseModel<UserModel>> updateCurrentUserObservable(
      @Body UserRequestModel body) {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(Constants.FilePaths.User.USER_UPDATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).updateCurrentUserObservable(body);
  }

  @Override public Call<ResponseModel<UserModel>> createUserCall(@Body UserRequestModel body) {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(Constants.FilePaths.User.USER_CREATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).createUserCall(body);
  }

  @Override public Call<ResponseModel<UserModel>> getUserCall(@Path("id") String id) {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(Constants.FilePaths.User.USER_GET_RESPONSE_OK);
    return delegate.returningResponse(response).getUserCall(id);
  }

  @Override public Call<ResponseModel<UserModel>> getCurrentUserCall() {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(
            Constants.FilePaths.User.USER_CURRENT_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getCurrentUserCall();
  }

  @Override
  public Call<ResponseModel<UserModel>> updateCurrentUserCall(@Body UserRequestModel body) {
    ResponseModel<UserModel> response =
        ModelGenerator.User.getResponseModel(Constants.FilePaths.User.USER_UPDATE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).updateCurrentUserCall(body);
  }
}
