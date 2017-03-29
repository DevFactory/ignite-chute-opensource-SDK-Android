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
package com.android.getchute.sdk.chutesdkandroid.retrofit;

import com.android.getchute.sdk.chutesdkandroid.Endpoints;
import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.api.service.AlbumService;
import com.android.getchute.sdk.chutesdkandroid.api.service.AssetService;
import com.android.getchute.sdk.chutesdkandroid.api.service.AuthService;
import com.android.getchute.sdk.chutesdkandroid.api.service.HeartService;
import com.android.getchute.sdk.chutesdkandroid.api.service.TagService;
import com.android.getchute.sdk.chutesdkandroid.api.service.UserService;
import com.android.getchute.sdk.chutesdkandroid.api.service.VoteService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

  private static RetrofitService instance;
  private Retrofit retrofit;

  private RetrofitService() {
    createRetrofit();
  }

  public static RetrofitService get() {
    if (instance == null) {
      synchronized (RetrofitService.class) {
        if (instance == null) {
          instance = new RetrofitService();
        }
      }
    }
    return instance;
  }

  private Gson gson() {
    Gson gson = new GsonBuilder().setFieldNamingPolicy(
        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    return gson;
  }

  private Retrofit createRetrofit() {
    Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .client(authClient().build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    retrofit = retrofitBuilder.baseUrl(Endpoints.BASE_URL)
        .build();
    return retrofit;
  }

  private OkHttpClient.Builder authClient() {
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.interceptors().add(new LoggingInterceptor());
    client.interceptors().add(TokenAuthenticationProvider.getInstance());
    return client;
  }

  public AuthService getAuthService() {
    return retrofit.create(AuthService.class);
  }

  public AlbumService getAlbumService() {
    return retrofit.create(AlbumService.class);
  }

  public AssetService getAssetService() {
    return retrofit.create(AssetService.class);
  }

  public HeartService getHeartService() {
    return retrofit.create(HeartService.class);
  }

  public VoteService getVoteService() {
    return retrofit.create(VoteService.class);
  }

  public TagService getTagService() {
    return retrofit.create(TagService.class);
  }

  public UserService getUserService() {
    return retrofit.create(UserService.class);
  }
}
