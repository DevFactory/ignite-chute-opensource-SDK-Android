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
package com.android.getchute.sdk.chutesdkandroid.api.service.model;

import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.ProfileBodyRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserBodyCreateRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserBodyUpdateRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserCreateRequestBody;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserUpdateRequestBody;
import com.android.getchute.sdk.chutesdkandroid.retrofit.RetrofitService;
import io.reactivex.Observable;
import retrofit2.Call;

public class GCUsers {

  public static class Observables {

    /**
     * Gets the user model for the currently authenticated user by his
     * Authorization token.
     * <p>
     * Returns full user info as a response.
     */
    public Observable<ResponseModel<UserModel>> getCurrent() {
      return RetrofitService.get().getUserService().getCurrentUserObservable();
    }

    /**
     * Gets user info for the provided ID.
     * <p>
     * Returns full user info as a response.
     *
     * @param userId The ID od the {@link UserModel} containing the information to be
     * retrieved.
     */
    public Observable<ResponseModel<UserModel>> get(String userId) {
      return RetrofitService.get().getUserService().getUserObservable(userId);
    }

    /**
     * Updates information of the currently authenticated user.
     *
     * @param email E-mail of the {@link UserModel} to be updated.
     * @param name Name of the {@link UserModel} to be updted.
     * @param company {@link UserModel} company.
     * @param title {@link UserModel} title.
     */
    public Observable<ResponseModel<UserModel>> update(String name, String email, String company,
        String title) {
      UserUpdateRequestBody body = new UserUpdateRequestBody();
      UserBodyUpdateRequestModel userBodyUpdateRequestModel = new UserBodyUpdateRequestModel();
      userBodyUpdateRequestModel.setEmail(email);
      userBodyUpdateRequestModel.setName(name);
      ProfileBodyRequestModel profileBodyRequestModel = new ProfileBodyRequestModel();
      profileBodyRequestModel.setCompany(company);
      profileBodyRequestModel.setTitle(title);
      userBodyUpdateRequestModel.setProfile(profileBodyRequestModel);
      body.setUser(userBodyUpdateRequestModel);
      body.setOauthToken(TokenAuthenticationProvider.getInstance().getToken());
      return RetrofitService.get().getUserService().updateCurrentUserObservable(body);
    }

    /**
     * Creates new user using email and password.
     */
    public Observable<ResponseModel<UserModel>> create(String email, String password) {
      UserCreateRequestBody body = new UserCreateRequestBody();
      UserBodyCreateRequestModel model = new UserBodyCreateRequestModel();
      model.setEmail(email);
      model.setPassword(password);
      model.setPasswordConfirmation(password);
      body.setUser(model);
      return RetrofitService.get().getUserService().createUserObservable(body);
    }
  }

  public static class Calls {

    /**
     * Gets the user model for the currently authenticated user by his
     * Authorization token.
     * <p>
     * Returns full user info as a response.
     */
    public Call<ResponseModel<UserModel>> getCurrent() {
      return RetrofitService.get().getUserService().getCurrentUserCall();
    }

    /**
     * Gets user info for the provided ID.
     * <p>
     * Returns full user info as a response.
     *
     * @param userId The ID od the {@link UserModel} containing the information to be
     * retrieved.
     */
    public Call<ResponseModel<UserModel>> get(String userId) {
      return RetrofitService.get().getUserService().getUserCall(userId);
    }

    /**
     * Updates information of the currently authenticated user.
     *
     * @param email E-mail of the {@link UserModel} to be updated.
     * @param name Name of the {@link UserModel} to be updted.
     * @param company {@link UserModel} company.
     * @param title {@link UserModel} title.
     */
    public Call<ResponseModel<UserModel>> update(String name, String email, String company,
        String title) {
      UserUpdateRequestBody body = new UserUpdateRequestBody();
      UserBodyUpdateRequestModel userBodyUpdateRequestModel = new UserBodyUpdateRequestModel();
      userBodyUpdateRequestModel.setEmail(email);
      userBodyUpdateRequestModel.setName(name);
      ProfileBodyRequestModel profileBodyRequestModel = new ProfileBodyRequestModel();
      profileBodyRequestModel.setCompany(company);
      profileBodyRequestModel.setTitle(title);
      userBodyUpdateRequestModel.setProfile(profileBodyRequestModel);
      body.setUser(userBodyUpdateRequestModel);
      body.setOauthToken(TokenAuthenticationProvider.getInstance().getToken());
      return RetrofitService.get().getUserService().updateCurrentUserCall(body);
    }

    /**
     * Creates new user using email and password.
     */
    public Call<ResponseModel<UserModel>> create(String email, String password, String passwordConfirmation) {
      UserCreateRequestBody body = new UserCreateRequestBody();
      UserBodyCreateRequestModel model = new UserBodyCreateRequestModel();
      model.setEmail(email);
      model.setPassword(password);
      model.setPasswordConfirmation(passwordConfirmation);
      body.setUser(model);
      return RetrofitService.get().getUserService().createUserCall(body);
    }
  }
}
