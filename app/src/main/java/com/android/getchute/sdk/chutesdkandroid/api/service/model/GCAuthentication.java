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

import com.android.getchute.sdk.chutesdkandroid.api.Chute;
import com.android.getchute.sdk.chutesdkandroid.model.LoginRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.LoginResponseModel;

import io.reactivex.Flowable;
import retrofit2.Call;
import rx.Observable;

public class GCAuthentication {

    /**
     * Authenticates user with given client ID and secret.
     */
    public Observable<LoginResponseModel> loginObservable(String username, String password, String clientId, String clientSecret) {
        LoginRequestModel loginRequestModel = new LoginRequestModel(username, password, clientId, clientSecret);
        return Chute.getAuthService().loginObservableRequest(loginRequestModel);
    }

    public Flowable<LoginResponseModel> loginFlowable(String username, String password, String clientId, String clientSecret) {
        LoginRequestModel loginRequestModel = new LoginRequestModel(username, password, clientId, clientSecret);
        return Chute.getAuthService().loginFlowableRequest(loginRequestModel);
    }

    public Call<LoginResponseModel> loginCall(String username, String password, String clientId, String clientSecret) {
        LoginRequestModel loginRequestModel = new LoginRequestModel(username, password, clientId, clientSecret);
        return Chute.getAuthService().loginCallRequest(loginRequestModel);
    }
}
