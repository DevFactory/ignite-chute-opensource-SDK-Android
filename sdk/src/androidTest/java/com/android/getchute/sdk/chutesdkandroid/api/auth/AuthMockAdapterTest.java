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
package com.android.getchute.sdk.chutesdkandroid.api.auth;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.ImmediateSchedulersRule;
import com.android.getchute.sdk.chutesdkandroid.api.RetrofitTestService;
import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.api.service.auth.AuthService;
import com.android.getchute.sdk.chutesdkandroid.model.LoginRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.LoginResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelGenerator;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Call;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AuthMockAdapterTest {

  private AuthService mockAuthService;
  private AuthService mockFailedAuthService;

  @Before
  public void init() throws Exception {
    mockAuthService = RetrofitTestService.get().getMockAuthService();
    mockFailedAuthService = RetrofitTestService.get().getMockFailedAuthService();
    TokenAuthenticationProvider.init(InstrumentationRegistry.getContext());
    TokenAuthenticationProvider.getInstance().setToken(Constants.MOCK_OAUTH_TOKEN);
  }

  @Test
  public void testAuthLoginCall() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.Login.getRequestModel();
    Call<LoginResponseModel> call = mockAuthService.loginCall(loginRequestModel);
    Response<LoginResponseModel> response = call.execute();
    Assert.assertTrue(response.isSuccessful());
    checkSuccessfulResult(response.body().getAccessToken(), response.body().getTokenType());
  }

  @Test
  public void testFailedAuthLoginCall() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.Login.getFailedRequestModel();
    Call<LoginResponseModel> call = mockFailedAuthService.loginCall(loginRequestModel);
    Response<LoginResponseModel> response = call.execute();
    checkFailedResult(response.body().getError(), response.body().getErrorDescription());
  }

  @Rule
  public final ImmediateSchedulersRule schedulers = new ImmediateSchedulersRule();

  @Test
  public void testAuthLoginObserver() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.Login.getRequestModel();
    Observable<LoginResponseModel> observable =
        mockAuthService.loginObservable(loginRequestModel);
    TestObserver<LoginResponseModel> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    checkSuccessfulResult(testObserver.values().get(0).getAccessToken(),
        testObserver.values().get(0).getTokenType());
    Assert.assertTrue(testObserver.isDisposed());
  }

  @SmallTest
  public void testFailedAuthLoginObserver() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.Login.getFailedRequestModel();
    Observable<LoginResponseModel> observable =
        mockFailedAuthService.loginObservable(loginRequestModel);
    TestObserver<LoginResponseModel> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);
    testObserver.assertComplete();
    checkFailedResult(testObserver.values().get(0).getError(),
        testObserver.values().get(0).getErrorDescription());
    Assert.assertTrue(testObserver.isDisposed());
  }

  private void checkSuccessfulResult(String accessToken, String tokenType) {
    Assert.assertEquals("1234567890abcd", accessToken);
    Assert.assertEquals("bearer", tokenType);
  }

  private void checkFailedResult(String error, String description) {
    Assert.assertEquals("invalid_resource_owner", error);
    Assert.assertEquals(
        "The provided resource owner credentials are not valid, or resource owner cannot be found",
        description);
  }
}