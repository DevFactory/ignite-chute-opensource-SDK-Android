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

import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkInfoModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
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

public class AssetCopyMockAdapterTest extends BaseMockAssetAdapterTest {

  @Test
  public void testAssetCopyCall() throws Exception {
    Call<ResponseModel<AssetModel>> call =
        mockAssetService.copyCall(ALBUM_ID, ASSET_ID, NEW_ALBUM_ID);
    Response<ResponseModel<AssetModel>> response = call.execute();
    AssetModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(200, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedAssetModel()), gson.toJson(actual), false);
  }

  @Test
  public void testAssetCopyFailedMissingTokenCall() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    Call<ResponseModel<AssetModel>> call =
        mockFailedAssetService.copyCall(ALBUM_ID, ASSET_ID, NEW_ALBUM_ID);
    Response<ResponseModel<AssetModel>> response = call.execute();
    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testAssetCopyObserver() throws Exception {
    Observable<ResponseModel<AssetModel>> observable =
        mockAssetService.copyObservable(ALBUM_ID, ASSET_ID, NEW_ALBUM_ID);
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
  public void testAssetCopyFailedMissingTokenObserver() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken("");
    Observable<ResponseModel<AssetModel>> observable =
        mockFailedAssetService.copyObservable(ALBUM_ID, ASSET_ID, NEW_ALBUM_ID);
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
            "https://getchute.com/v2/albums/2586173/assets/3530168632",
            "AlbumAsset Details");
    LinkInfoModel exif =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586173/assets/3530168632/exif",
            "Exif Details");
    LinkInfoModel geo =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586173/assets/3530168632/geo",
            "Geo Details");
    LinkInfoModel heart =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586173/assets/3530168632/hearts",
            "Hearts");
    LinkInfoModel vote =
        ModelBluePrint.createLinkInfoModel(
            "https://getchute.com/v2/albums/2586173/assets/3530168632/votes",
            "Votes");
    LinkModel linkModel =
        ModelBluePrint.createLinkModel(self, null, geo, exif, heart, vote);
    List<String> tags = new ArrayList<>();
    tags.add("mwa");
    AssetModel assetModel =
        ModelBluePrint.createAssetModel("3530168632", linkModel, "2017-03-14T09:49:43.280Z",
            "2017-04-25T07:50:22.936Z", "3Q3610tmhq", "image", "3530168632",
            tags, 0, 0, "http://media.getchute.com/media/3Q3610tmhq/75x75",
            "https://media.getchute.com/media/3Q3610tmhq", null);
    return assetModel;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Unauthorized", 401, 2,
        "https://api.getchute.com/v2/albums/2586175/assets/3517506078/copy/2586173", null);
  }
}