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
package com.android.getchute.sdk.chutesdkandroid.api.heart;

import com.android.getchute.sdk.chutesdkandroid.model.HeartModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class HeartCountMockAdapterTest extends BaseMockHeartAdapterTest {

  private static final String ALBUM_ID = "2586175";
  private static final String ASSET_ID = "3517506078";
  private static final String ASSET_ID_ERRONEOUS = "35";

  @Test
  public void testHeartCountCall() throws Exception {
    Call<ResponseModel<HeartModel>> call =
        mockHeartService.heartCountCall(ALBUM_ID, ASSET_ID);
    Response<ResponseModel<HeartModel>> response = call.execute();
    HeartModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    Assert.assertEquals(1, actual.getCount());
  }

  @Test
  public void testHeartCountFailedAssetNotFoundCall() throws Exception {
    Call<ResponseModel<HeartModel>> call =
        mockFailedHeartService.heartCountCall(ALBUM_ID, ASSET_ID_ERRONEOUS);
    Response<ResponseModel<HeartModel>> response = call.execute();
    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testHeartCountObserver() throws Exception {
    Observable<ResponseModel<HeartModel>> observable =
        mockHeartService.heartCountObservable(ALBUM_ID, ASSET_ID);
    TestObserver<ResponseModel<HeartModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    HeartModel actual = testObserver.values().get(0).getData();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    Assert.assertEquals(1, actual.getCount());
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testHeartCountFailedAssetNotFoundObserver() throws Exception {
    Observable<ResponseModel<HeartModel>> observable =
        mockFailedHeartService.heartCountObservable(ALBUM_ID, ASSET_ID_ERRONEOUS);
    TestObserver<ResponseModel<HeartModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    ResponseStatusModel actual = testObserver.values().get(0).getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
    Assert.assertTrue(testObserver.isDisposed());
  }


  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Not Found", 404, 2,
        "https://api.getchute.com/v2/albums/2586175/assets/35/hearts", null);
  }
}