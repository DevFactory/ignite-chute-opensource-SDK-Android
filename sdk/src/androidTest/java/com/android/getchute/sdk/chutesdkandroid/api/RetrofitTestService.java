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
package com.android.getchute.sdk.chutesdkandroid.api;

import com.android.getchute.sdk.chutesdkandroid.api.album.MockAlbumService;
import com.android.getchute.sdk.chutesdkandroid.api.album.MockFailedAlbumService;
import com.android.getchute.sdk.chutesdkandroid.api.asset.MockAssetFailedService;
import com.android.getchute.sdk.chutesdkandroid.api.asset.MockAssetService;
import com.android.getchute.sdk.chutesdkandroid.api.auth.MockAuthService;
import com.android.getchute.sdk.chutesdkandroid.api.auth.MockFailedAuthService;
import com.android.getchute.sdk.chutesdkandroid.api.service.album.AlbumService;
import com.android.getchute.sdk.chutesdkandroid.api.service.asset.AssetService;
import com.android.getchute.sdk.chutesdkandroid.api.service.auth.AuthService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class RetrofitTestService {

  private static RetrofitTestService instance;
  private MockRetrofit mockRetrofit;
  private Gson gson;

  private RetrofitTestService() {
    createMockRetrofit();
  }

  public static RetrofitTestService get() {
    if (instance == null) {
      synchronized (RetrofitTestService.class) {
        if (instance == null) {
          instance = new RetrofitTestService();
        }
      }
    }
    return instance;
  }

  public Gson getGson() {
    return gson;
  }

  private MockRetrofit createMockRetrofit() {
    gson = new GsonBuilder().setFieldNamingPolicy(
        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://test.com")
        .client(new OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

    NetworkBehavior networkBehavior = NetworkBehavior.create();
    networkBehavior.setFailurePercent(0);

    mockRetrofit = new MockRetrofit.Builder(retrofit)
        .networkBehavior(networkBehavior)
        .build();
    return mockRetrofit;
  }

  private BehaviorDelegate<AuthService> getAuthBehaviorDelegate() {
    return mockRetrofit.create(AuthService.class);
  }

  private BehaviorDelegate<AlbumService> getAlbumBehaviorDelegate() {
    return mockRetrofit.create(AlbumService.class);
  }

  private BehaviorDelegate<AssetService> getAssetBehaviorDelegate() {
    return mockRetrofit.create(AssetService.class);
  }

  public AuthService getMockAuthService() {
    return new MockAuthService(getAuthBehaviorDelegate());
  }

  public AuthService getMockFailedAuthService() {
    return new MockFailedAuthService(getAuthBehaviorDelegate());
  }

  public AlbumService getMockAlbumService() {
    return new MockAlbumService(getAlbumBehaviorDelegate());
  }

  public AlbumService getMockFailedAlbumService() {
    return new MockFailedAlbumService(getAlbumBehaviorDelegate());
  }

  public AssetService getMockAssetService() {
    return new MockAssetService(getAssetBehaviorDelegate());
  }

  public AssetService getMockFailedAssetService() {
    return new MockAssetFailedService(getAssetBehaviorDelegate());
  }
}
