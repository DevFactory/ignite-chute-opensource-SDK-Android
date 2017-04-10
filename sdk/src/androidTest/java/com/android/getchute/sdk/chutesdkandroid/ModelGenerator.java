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
package com.android.getchute.sdk.chutesdkandroid;

import android.support.test.InstrumentationRegistry;
import android.util.Log;
import com.android.getchute.sdk.chutesdkandroid.model.LoginRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.LoginResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class ModelGenerator {

  private static final String TAG = ModelGenerator.class.getSimpleName();
  private static Gson gson = new GsonBuilder().setLenient().create();

  public static LoginRequestModel getLoginRequestModel() throws Exception {
    return gson.fromJson(
        FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
            FileNames.AUTH_REQUEST_SUCCESS), LoginRequestModel.class);
  }

  public static LoginRequestModel getLoginFailedRequestModel() throws Exception {
    return gson.fromJson(
        FileUtil.getStringFromFile(InstrumentationRegistry.getContext(),
            FileNames.AUTH_REQUEST_FAIL), LoginRequestModel.class);
  }

  public static LoginResponseModel getLoginResponseModel() {
    LoginResponseModel loginResponseModel = new LoginResponseModel();
    try {
      String jsonString = FileUtil.getStringFromFile(
          InstrumentationRegistry.getContext(), FileNames.AUTH_RESPONSE_SUCCESS);
      JSONObject json = new JSONObject(jsonString);
      loginResponseModel = gson.fromJson(json.toString(), LoginResponseModel.class);
    } catch (Exception e) {
      Log.e(TAG, "Failed to create LoginResponseModel: " + e.getMessage());
    }
    return loginResponseModel;
  }

  public static LoginResponseModel getLoginFailedResponseModel() {
    LoginResponseModel loginResponseModel = new LoginResponseModel();
    try {
      loginResponseModel = gson.fromJson(FileUtil.getStringFromFile(
          InstrumentationRegistry.getContext(), FileNames.AUTH_RESPONSE_FAIL),
          LoginResponseModel.class);
    } catch (Exception e) {
      Log.e(TAG, "Failed to create LoginResponseModel: " + e.getMessage());
    }
    return loginResponseModel;
  }
}
