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

import android.support.test.filters.SmallTest;
import android.test.InstrumentationTestCase;
import com.android.getchute.sdk.chutesdkandroid.ImmediateSchedulersRule;
import com.android.getchute.sdk.chutesdkandroid.ModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.api.RetrofitTestService;
import com.android.getchute.sdk.chutesdkandroid.api.service.auth.AuthService;
import com.android.getchute.sdk.chutesdkandroid.model.LoginRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.LoginResponseModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Rule;
import retrofit2.Call;
import retrofit2.Response;

public class AuthMockAdapterTest extends InstrumentationTestCase {

  private AuthService mockAuthService;
  private AuthService mockFailedAuthService;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    mockAuthService = RetrofitTestService.get().getMockAuthService();
    mockFailedAuthService = RetrofitTestService.get().getMockFailedAuthService();
  }

  @SmallTest
  public void testAuthLoginCall() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.getLoginRequestModel();

    Call<LoginResponseModel> login = mockAuthService.loginCall(loginRequestModel);
    Response<LoginResponseModel> response = login.execute();

    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals("1234567890abcd", response.body().getAccessToken());
    Assert.assertEquals("bearer", response.body().getTokenType());
  }

  @SmallTest
  public void testFailedAuthLoginCall() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.getLoginFailedRequestModel();
    Call<LoginResponseModel> login = mockFailedAuthService.loginCall(loginRequestModel);
    Response<LoginResponseModel> response = login.execute();

    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals("invalid_resource_owner", response.body().getError());
    Assert.assertEquals(
        "The provided resource owner credentials are not valid, or resource owner cannot be found",
        response.body().getErrorDescription());
  }

  @Rule
  public final ImmediateSchedulersRule schedulers = new ImmediateSchedulersRule();

  @SmallTest
  public void testAuthLoginObserver() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.getLoginRequestModel();

    Observable<LoginResponseModel> loginObservable =
        mockAuthService.loginObservable(loginRequestModel);
    TestObserver<LoginResponseModel> testObserver = loginObservable.test();
    loginObservable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertNoErrors();
    Assert.assertEquals("1234567890abcd", testObserver.values().get(0).getAccessToken());
    Assert.assertEquals("bearer", testObserver.values().get(0).getTokenType());
    assertTrue(testObserver.isDisposed());
  }

  @SmallTest
  public void testFailedAuthLoginObserver() throws Exception {
    LoginRequestModel loginRequestModel = ModelGenerator.getLoginFailedRequestModel();

    Observable<LoginResponseModel> loginObservable =
        mockFailedAuthService.loginObservable(loginRequestModel);
    TestObserver<LoginResponseModel> testObserver = loginObservable.test();
    loginObservable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    Assert.assertEquals("invalid_resource_owner", testObserver.values().get(0).getError());
    Assert.assertEquals(
        "The provided resource owner credentials are not valid, or resource owner cannot be found",
        testObserver.values().get(0).getErrorDescription());
    assertTrue(testObserver.isDisposed());
  }
}