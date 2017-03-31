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
package com.android.getchute.sdk.chutesdkandroid.api.service.user;

import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.ProfileRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.UserRequestModel;
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
    public static Observable<ResponseModel<UserModel>> getCurrent() {
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
    public static Observable<ResponseModel<UserModel>> get(String userId) {
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
    public static Observable<ResponseModel<UserModel>> update(String email, String name,
        String username,
        String company, String title) {
      return RetrofitService.get().getUserService().updateCurrentUserObservable(
          getBody(email, name, username, company, title, null, null));
    }

    /**
     * Creates new user using email and password.
     */
    public static Observable<ResponseModel<UserModel>> create(String email, String name,
        String username,
        String company, String title, String password,
        String passwordConfirmation) {
      return RetrofitService.get()
          .getUserService()
          .createUserObservable(
              getBody(email, name, username, company, title, password, passwordConfirmation));
    }
  }

  public static class Calls {

    /**
     * Gets the user model for the currently authenticated user by his
     * Authorization token.
     * <p>
     * Returns full user info as a response.
     */
    public static Call<ResponseModel<UserModel>> getCurrent() {
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
    public static Call<ResponseModel<UserModel>> get(String userId) {
      return RetrofitService.get().getUserService().getUserCall(userId);
    }

    /**
     * Updates information of the currently authenticated user.
     *
     * @param email E-mail of the {@link UserModel} to be updated.
     * @param name Name of the {@link UserModel} to be updted.
     * @param username Username of the {@link UserModel} to be updated.
     * @param company {@link UserModel} company.
     * @param title {@link UserModel} title.
     */
    public static Call<ResponseModel<UserModel>> update(String email, String name, String username,
        String company,
        String title) {
      return RetrofitService.get()
          .getUserService()
          .updateCurrentUserCall(getBody(email, name, username, company, title, null, null));
    }

    /**
     * Creates new user using email and password.
     *
     * @param email E-mail of the {@link UserModel}.
     * @param name Name of the {@link UserModel}.
     * @param username Username of the {@link UserModel}.
     * @param company {@link UserModel} company.
     * @param title {@link UserModel} title.
     * @param password {@link UserModel} password.
     * @param passwordConfirmation {@link UserModel} retyped password.
     */
    public static Call<ResponseModel<UserModel>> create(String email, String name, String username,
        String company, String title, String password,
        String passwordConfirmation) {
      return RetrofitService.get()
          .getUserService()
          .createUserCall(
              getBody(email, name, username, company, title, password, passwordConfirmation));
    }
  }

  private static UserRequestModel getBody(String email, String name, String username,
      String company,
      String title, String password, String confirmPassword) {
    UserRequestModel userRequestModel = new UserRequestModel();
    userRequestModel.setEmail(email);
    userRequestModel.setName(name);
    userRequestModel.setUsername(username);
    ProfileRequestModel profileRequestModel = new ProfileRequestModel();
    profileRequestModel.setCompany(company);
    profileRequestModel.setTitle(title);
    userRequestModel.setProfile(profileRequestModel);
    userRequestModel.setPassword(password);
    userRequestModel.setConfirmPassword(confirmPassword);
    return userRequestModel;
  }
}
