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

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkInfoModel;
import com.android.getchute.sdk.chutesdkandroid.model.LinkModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelBluePrint;
import com.android.getchute.sdk.chutesdkandroid.model.ModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.ResponseStatusModel;
import com.android.getchute.sdk.chutesdkandroid.model.UserModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumRequestModel;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import retrofit2.Call;
import retrofit2.Response;

public class AlbumCreateMockAdapterTest extends BaseMockAlbumAdapterTest {

  @Test
  public void testAlbumCreateCall() throws Exception {
    AlbumRequestModel albumRequestModel =
        ModelGenerator.Album.getRequestModel(Constants.FilePaths.Album.CREATE_REQUEST_BODY_NAME);
    Call<ResponseModel<AlbumModel>> call =
        mockAlbumService.createAlbumCall(albumRequestModel);
    Response<ResponseModel<AlbumModel>> response = call.execute();
    AlbumModel actual = response.body().getData();
    Assert.assertTrue(response.isSuccessful());
    Assert.assertEquals(201, response.body().getResponse().getCode());
    JSONAssert.assertEquals(gson.toJson(getExpectedAlbumModel()), gson.toJson(actual), false);
  }

  @Test
  public void testAlbumCreateFailedWrongTokenCall() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken(Constants.MOCK_ERROR_TOKEN);
    AlbumRequestModel albumRequestModel =
        ModelGenerator.Album.getFailedRequestModel(Constants.FilePaths.Album.CREATE_REQUEST_BODY_PARENT_ID);
    Call<ResponseModel<AlbumModel>> call =
        mockFailedAlbumService.createAlbumCall(albumRequestModel);
    Response<ResponseModel<AlbumModel>> response = call.execute();

    ResponseStatusModel actual = response.body().getResponse();
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), gson.toJson(actual),
        false);
  }

  @Test
  public void testAlbumCreateObserver() throws Exception {
    AlbumRequestModel albumRequestModel =
        ModelGenerator.Album.getRequestModel(Constants.FilePaths.Album.CREATE_REQUEST_BODY_NAME);
    Observable<ResponseModel<AlbumModel>> observable =
        mockAlbumService.createAlbumObservable(albumRequestModel);
    TestObserver<ResponseModel<AlbumModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertNoErrors();
    AlbumModel actual = testObserver.values().get(0).getData();
    JSONAssert.assertEquals(gson.toJson(getExpectedAlbumModel()), gson.toJson(actual), false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  @Test
  public void testAlbumCreateFailedWrongTokenObserver() throws Exception {
    TokenAuthenticationProvider.getInstance().setToken(Constants.MOCK_ERROR_TOKEN);
    AlbumRequestModel albumRequestModel =
        ModelGenerator.Album.getFailedRequestModel(Constants.FilePaths.Album.CREATE_REQUEST_BODY_PARENT_ID);
    Observable<ResponseModel<AlbumModel>> observable =
        mockFailedAlbumService.createAlbumObservable(albumRequestModel);
    TestObserver<ResponseModel<AlbumModel>> testObserver = observable.test();
    observable.subscribeOn(Schedulers.io())
        .subscribe(testObserver);

    testObserver.assertComplete();
    ResponseStatusModel responseStatusModel = testObserver.values().get(0).getResponse();
    String actual = gson.toJson(responseStatusModel);
    JSONAssert.assertEquals(gson.toJson(getExpectedStatusResponseModel()), actual, false);
    Assert.assertTrue(testObserver.isDisposed());
  }

  private AlbumModel getExpectedAlbumModel() {
    LinkInfoModel self =
        ModelBluePrint.createLinkInfoModel("https://getchute.com/v2/albums/2587833",
            "Album Details");
    LinkInfoModel assets =
        ModelBluePrint.createLinkInfoModel("https://getchute.com/v2/albums/2587833/assets",
            "Asset Listing");
    LinkModel linkModel =
        ModelBluePrint.createLinkModel(self, assets, null, null, null, null);
    UserModel userModel = ModelBluePrint.createUserModel("86004443", "2015-05-05T11:04:29.166Z",
        "2017-03-30T12:03:58.598Z", "Oliver", "oli0290",
        "https://instagram.fotp1-1.fna.fbcdn.net/t51.2885-19/11906329_960233084022564_1448528159_a.jpg",
        "2017-03-20T10:09:27.379Z", true);
    AlbumModel albumModel =
        ModelBluePrint.createAlbumModel("2587833", linkModel, "2017-04-10T14:56:42.521Z",
            "2017-04-10T14:56:42.521Z", "aRdfmery", "Test Album", false, userModel);
    return albumModel;
  }

  private ResponseStatusModel getExpectedStatusResponseModel() {
    return ModelBluePrint.createResponseStatusModel("Unauthorized", 401, 2,
        "https://api.getchute.com/v2/albums", null);
  }
}