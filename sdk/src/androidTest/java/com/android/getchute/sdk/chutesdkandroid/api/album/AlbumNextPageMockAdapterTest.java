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
package com.android.getchute.sdk.chutesdkandroid.api.album;

import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkInfoModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class AlbumNextPageMockAdapterTest extends BaseMockAlbumAdapterTest {

  private String nextPageUrl = "https://api.getchute.com/v2/albums?page=2&per_page=2";

  @Test
  public void testAlbumNextPageCall() throws Exception {
    Call<ListResponseModel<AlbumModel>> call =
        mockAlbumService.getNextPageCall(nextPageUrl);
    Response<ListResponseModel<AlbumModel>> response = call.execute();
    AlbumModel actual = response.body().getData().get(0);
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedAlbumModel()), gson.toJson(actual), false);
  }

  @Test
  public void testAalbumNextPageFailedMissingTokenCall() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    Call<ListResponseModel<AlbumModel>> call =
        mockFailedAlbumService.getNextPageCall(nextPageUrl);
    Response<ListResponseModel<AlbumModel>> response = call.execute();
    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testAlbumNextPageObserver() throws Exception {
    Observable<ListResponseModel<AlbumModel>> observable =
        mockAlbumService.getNextPageObservable(nextPageUrl);
    TestObserver<ListResponseModel<AlbumModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    AlbumModel actual = testObserver.values().get(0).getData().get(0);
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    JSONAssert.assertEquals(gson.toJson(getExpectedAlbumModel()), gson.toJson(actual), false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testAlbumNextPageFailedMissingTokenObserver() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    Observable<ListResponseModel<AlbumModel>> observable =
        mockFailedAlbumService.getNextPageObservable(nextPageUrl);
    TestObserver<ListResponseModel<AlbumModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    ResponseStatusModel actual = testObserver.values().get(0).getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  private AlbumModel getExpectedAlbumModel() {
    LinkInfoModel self =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586173",
            "Album Details");
    LinkInfoModel asset =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586173/assets",
            "Asset Listing");
    LinkModel linkModel =
        ModelBluePrint.createLinkModel(self, asset, null, null, null, null);
    UserModel userModel = ModelBluePrint.createUserModel("86004443", "2015-05-05T11:04:29.166Z",
        "2017-04-21T21:08:38.035Z", "Oliver Dimitrov", "oli2290",
        "https://instagram.fotp1-1.fna.fbcdn.net/t51.2885-19/11906329_960233084022564_1448528159_a.jpg",
        "2017-03-20T10:09:27.379Z", true);
    AlbumModel albumModel =
        ModelBluePrint.createAlbumModel("2586173", linkModel, "2017-03-14T09:20:59.038Z",
            "2017-04-11T00:32:01.948Z", "aQMtbrwz", "New test", true, userModel);
    return albumModel;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Unauthorized", 401, 2,
        "https://api.getchute.com/v2/albums?page=2&per_page=2", null);
  }
}