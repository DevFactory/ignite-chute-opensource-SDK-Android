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
package com.android.getchute.sdk.chutesdkandroid.api.tags;

import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
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

public class TagReplaceMockAdapterTest extends BaseMockTagAdapterTest {

  @Test
  public void testTagReplaceCall() throws Exception {
    Call<ListResponseModel<String>> call =
        mockTagService.replaceTagsCall(ALBUM_ID, ASSET_ID, ModelGenerator.Tag.getRequestBody());
    Response<ListResponseModel<String>> response = call.execute();
    List<String> actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpected()), gson.toJson(actual), false);
  }

  @Test
  public void testTagReplaceFailedMissingTokenCall() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    Call<ListResponseModel<String>> call =
        mockFailedTagService.replaceTagsCall(ALBUM_ID, ASSET_ID, ModelGenerator.Tag.getRequestBody());
    Response<ListResponseModel<String>> response = call.execute();
    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testTagReplaceObserver() throws Exception {
    Observable<ListResponseModel<String>> observable =
        mockTagService.replaceTagsObservable(ALBUM_ID, ASSET_ID, ModelGenerator.Tag.getRequestBody());
    TestObserver<ListResponseModel<String>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    List<String> actual = testObserver.values().get(0).getData();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    JSONAssert.assertEquals(gson.toJson(getExpected()), gson.toJson(actual), false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testTagReplaceFailedMissingTokenObserver() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    Observable<ListResponseModel<String>> observable =
        mockFailedTagService.replaceTagsObservable(ALBUM_ID, ASSET_ID,
            ModelGenerator.Tag.getRequestBody());
    TestObserver<ListResponseModel<String>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    ResponseStatusModel actual = testObserver.values().get(0).getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  private List<String> getExpected() {
    List<String> result = new ArrayList<>();
    result.add("olympics");
    result.add("sochi");
    return result;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Unauthorized", 401, 2,
        "https://api.getchute.com/v2/albums/2586175/assets/3517506078/tags", null);
  }
}