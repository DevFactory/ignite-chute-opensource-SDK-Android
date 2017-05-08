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

import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkInfoModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class AlbumGetMockAdapterTest extends BaseMockAlbumAdapterTest {

  @Test
  public void testAlbumGetCall() throws Exception {
    Call<ResponseModel<AlbumModel>> call =
        mockAlbumService.getAlbumCall(ALBUM_ID);
    Response<ResponseModel<AlbumModel>> response = call.execute();
    AlbumModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedAlbumModel()), gson.toJson(actual), false);
  }

  @Test
  public void testAlbumGetFailedNonexistentAlbumCall() throws Exception {
    Call<ResponseModel<AlbumModel>> call =
        mockFailedAlbumService.getAlbumCall(ALBUM_ID_ERRONEOUS);
    Response<ResponseModel<AlbumModel>> response = call.execute();

    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testAlbumGetObserver() throws Exception {
    Observable<ResponseModel<AlbumModel>> observable =
        mockAlbumService.getAlbumObservable(ALBUM_ID);
    TestObserver<ResponseModel<AlbumModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    AlbumModel actual = testObserver.values().get(0).getData();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    JSONAssert.assertEquals(gson.toJson(getExpectedAlbumModel()), gson.toJson(actual), false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testAlbumGetFailedNonexistentAlbumObserver() throws Exception {
    Observable<ResponseModel<AlbumModel>> observable =
        mockFailedAlbumService.getAlbumObservable(ALBUM_ID_ERRONEOUS);
    TestObserver<ResponseModel<AlbumModel>> testObserver = observable.test();
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
        ModelBluePrint.createLinkInfoModel("https://getchute.com/v2/albums/2586434",
            "Album Details");
    LinkInfoModel assets =
        ModelBluePrint.createLinkInfoModel("https://getchute.com/v2/albums/2586434/assets",
            "Asset Listing");
    LinkModel linkModel =
        ModelBluePrint.createLinkModel(self, assets, null, null, null, null);
    UserModel userModel = ModelBluePrint.createUserModel("86004443", "2015-05-05T11:04:29.166Z",
        "2017-03-30T12:03:58.598Z", "olga", "olala00",
        "https://instagram.fotp1-1.fna.fbcdn.net/t51.2885-19/11906329_960233084022564_1448528159_a.jpg",
        "2017-03-20T10:09:27.379Z", true);
    AlbumModel albumModel =
        ModelBluePrint.createAlbumModel("2586173", linkModel, "2017-03-14T09:20:59.038Z",
            "2017-03-14T09:32:53.991Z", "aQMtbrwz", "Test", false, userModel);
    return albumModel;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Not Found", 404, 2,
        "https://api.getchute.com/v2/albums/25", null);
  }
}