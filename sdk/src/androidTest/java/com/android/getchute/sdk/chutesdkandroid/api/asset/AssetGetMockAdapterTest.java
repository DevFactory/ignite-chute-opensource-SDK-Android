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
package com.android.getchute.sdk.chutesdkandroid.api.asset;

import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkInfoModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class AssetGetMockAdapterTest extends BaseMockAssetAdapterTest {

  @Test
  public void testAssetGetCall() throws Exception {
    Call<ResponseModel<AssetModel>> call =
        mockAssetService.getCall(ALBUM_ID, ASSET_ID);
    Response<ResponseModel<AssetModel>> response = call.execute();
    AssetModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedAssetModel()), gson.toJson(actual), false);
  }

  @Test
  public void testAssetGetFailedAssetNotFoundCall() throws Exception {
    Call<ResponseModel<AssetModel>> call =
        mockFailedAssetService.getCall(ALBUM_ID, ASSET_ID_ERRONEOUS);
    Response<ResponseModel<AssetModel>> response = call.execute();
    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testAssetGetObserver() throws Exception {
    Observable<ResponseModel<AssetModel>> observable =
        mockAssetService.getObservable(ALBUM_ID, ASSET_ID);
    TestObserver<ResponseModel<AssetModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    AssetModel actual = testObserver.values().get(0).getData();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    JSONAssert.assertEquals(gson.toJson(getExpectedAssetModel()), gson.toJson(actual), false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testAssetGetFailedAssetNotFoundObserver() throws Exception {
    Observable<ResponseModel<AssetModel>> observable =
        mockFailedAssetService.getObservable(ALBUM_ID, ASSET_ID_ERRONEOUS);
    TestObserver<ResponseModel<AssetModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    ResponseStatusModel actual = testObserver.values().get(0).getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  private AssetModel getExpectedAssetModel() {
    LinkInfoModel self =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586175/assets/3517506078",
            "AlbumAsset Details");
    LinkInfoModel exif =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586175/assets/3517506078/exif",
            "Exif Details");
    LinkInfoModel geo =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586175/assets/3517506078/geo",
            "Geo Details");
    LinkInfoModel heart =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586175/assets/3517506078/hearts",
            "Hearts");
    LinkInfoModel vote =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586175/assets/3517506078/votes",
            "Votes");
    LinkModel linkModel =
        ModelBluePrint.createLinkModel(self, null, geo, exif, heart, vote);
    UserModel userModel = ModelBluePrint.createUserModel("86004443", "2015-05-05T11:04:29.166Z",
        "2017-04-21T21:08:38.035Z", "olga", "olala00",
        "https://instagram.fotp1-1.fna.fbcdn.net/t51.2885-19/11906329_960233084022564_1448528159_a.jpg",
        "2017-03-20T10:09:27.379Z", true);
    AssetModel assetModel =
        ModelBluePrint.createAssetModel("3517506078", linkModel, "2017-03-30T10:54:36.615Z",
            "2017-04-18T16:52:33.405Z", "3QnPU1mitx", "image", "3527978451",
            new ArrayList<String>(), 0, 0, "http://media.getchute.com/media/3QnPU1mitx/75x75",
            "https://media.getchute.com/media/3QnPU1mitx", userModel);
    return assetModel;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Not Found", 404, 2,
        "https://api.getchute.com/v2/albums/2586175/assets/352797845", null);
  }
}