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
package com.android.getchute.sdk.chutesdkandroid.api.user;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.OauthAppModel;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserRequestModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class UserUpdateMockAdapterTest extends BaseMockUserAdapterTest {

  @Test
  public void testUserUpdateCall() throws Exception {
    UserRequestModel userRequestModel =
        ModelGenerator.User.getRequestModel(Constants.FilePaths.User.USER_UPDATE_REQUEST);
    Call<ResponseModel<UserModel>> call =
        mockUserService.updateCurrentUserCall(userRequestModel);
    Response<ResponseModel<UserModel>> response = call.execute();
    UserModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedUserModel()), gson.toJson(actual), false);
  }

  @Test
  public void testUserUpdateFailedMissingTokenCall() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    UserRequestModel userRequestModel=
        ModelGenerator.User.getFailedRequestModel(Constants.FilePaths.User.USER_UPDATE_REQUEST);
    Call<ResponseModel<UserModel>> call =
        mockFailedUserService.updateCurrentUserCall(userRequestModel);
    Response<ResponseModel<UserModel>> response = call.execute();

    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testUserUpdateObserver() throws Exception {
    UserRequestModel userRequestModel =
        ModelGenerator.User.getRequestModel(Constants.FilePaths.User.USER_UPDATE_REQUEST);
    Observable<ResponseModel<UserModel>> observable =
        mockUserService.updateCurrentUserObservable(userRequestModel);
    TestObserver<ResponseModel<UserModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertNoErrors();
    UserModel actual = testObserver.values().get(0).getData();
    JSONAssert.assertEquals(gson.toJson(getExpectedUserModel()), gson.toJson(actual), false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testUserUpdateFailedMissingTokenObserver() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    UserRequestModel userRequestModel=
        ModelGenerator.User.getFailedRequestModel(Constants.FilePaths.User.USER_UPDATE_REQUEST);
    Observable<ResponseModel<UserModel>> observable =
        mockFailedUserService.updateCurrentUserObservable(userRequestModel);
    TestObserver<ResponseModel<UserModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    ResponseStatusModel responseStatusModel = testObserver.values().get(0).getResponse();
    String actual = gson.toJson(responseStatusModel);
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), actual, false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  private UserModel getExpectedUserModel() {
    UserModel userModel = new UserModel();
    userModel.setId("726");
    userModel.setName("Olga Lazarova");
    userModel.setUsername("ola");
    userModel.setCreatedAt("2012-02-15T22:59:17.000Z");
    userModel.setUpdatedAt("2017-04-28T15:10:19.846Z");
    userModel.setEmail("olga@getchute.com");
    userModel.setAvatar("https://avatars.io/facebook/531094438/medium");
    userModel.setLastLogin("2017-03-24T15:18:42.292Z");
    userModel.setOauthToken("123456");
    userModel.setAdmin(true);
    OauthAppModel app = new OauthAppModel();
    app.setName("Chute PhotoPicker+");
    app.setId("52");
    userModel.setOauthApp(app);
    return userModel;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Unauthorized", 401, 2,
        "https://api.getchute.com/v2/me", null);
  }
}