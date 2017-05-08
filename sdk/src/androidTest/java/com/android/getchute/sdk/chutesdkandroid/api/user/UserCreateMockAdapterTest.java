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
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class UserCreateMockAdapterTest extends BaseMockUserAdapterTest {

  @Test
  public void testUserCreateCall() throws Exception {
    UserRequestModel userRequestModel =
        ModelGenerator.User.getRequestModel(Constants.FilePaths.User.USER_CREATE_REQUEST_SUCCESS);
    Call<ResponseModel<UserModel>> call =
        mockUserService.createUserCall(userRequestModel);
    Response<ResponseModel<UserModel>> response = call.execute();
    UserModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(201, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedUserModel()), gson.toJson(actual), false);
  }

  @Test
  public void testUserCreateFailedEmailAlreadyTakenCall() throws Exception {
    UserRequestModel userRequestModel=
        ModelGenerator.User.getFailedRequestModel(Constants.FilePaths.User.USER_CREATE_REQUEST_FAIL);
    Call<ResponseModel<UserModel>> call =
        mockFailedUserService.createUserCall(userRequestModel);
    Response<ResponseModel<UserModel>> response = call.execute();

    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testUserCreateObserver() throws Exception {
    UserRequestModel userRequestModel =
        ModelGenerator.User.getRequestModel(Constants.FilePaths.User.USER_CREATE_REQUEST_SUCCESS);
    Observable<ResponseModel<UserModel>> observable =
        mockUserService.createUserObservable(userRequestModel);
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
  public void testUserCreateFailedEmailAlreadyTakenObserver() throws Exception {
    UserRequestModel userRequestModel=
        ModelGenerator.User.getFailedRequestModel(Constants.FilePaths.User.USER_CREATE_REQUEST_FAIL);
    Observable<ResponseModel<UserModel>> observable =
        mockFailedUserService.createUserObservable(userRequestModel);
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
    userModel.setId("143682587");
    userModel.setCreatedAt("2017-04-28T15:06:30.118Z");
    userModel.setUpdatedAt("2017-04-28T15:06:30.118Z");
    userModel.setAvatar("https://avatars.io/gravatar/chute.test.email@gmail.com/medium");
    userModel.setOauthToken("1234567");
    OauthAppModel app = new OauthAppModel();
    app.setName("Chute-Login");
    app.setId("5214");
    List<String> scopes = new ArrayList<>();
    scopes.add("login");
    app.setScopes(scopes);
    userModel.setOauthApp(app);
    return userModel;
  }


  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Email has already been taken", 400, 2,
        "https://api.getchute.com/v2/users", null);
  }
}