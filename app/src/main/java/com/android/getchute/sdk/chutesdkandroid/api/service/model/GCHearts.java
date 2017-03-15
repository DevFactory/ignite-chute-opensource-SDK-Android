/**
 Copyright (c) 2011, Chute Corporation. All rights reserved.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.
 * Neither the name of the  Chute Corporation nor the names
 of its contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 OF THE POSSIBILITY OF SUCH DAMAGE.
 **/
package com.android.getchute.sdk.chutesdkandroid.api.service.model;

import com.android.getchute.sdk.chutesdkandroid.api.Chute;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.HeartModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import retrofit2.Call;
import rx.Observable;

public class GCHearts {

  public static class Observables {

    /**
     * Gets a number of all hearts associated with an asset in a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} that holds the hearted asset.
     * @param assetId The ID of the hearted {@link AssetModel}
     */
    public Observable<ResponseModel<HeartModel>> heartCount(String albumId, String assetId) {
      return Chute.getHeartService().heartCountObservable(albumId, assetId);
    }

    /**
     * Removes an existing heart from an asset.
     * <p>
     * The heart will be marked for the current user executing the requests.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the hearted asset.
     * @param assetId The ID of the hearted {@link AssetModel}
     */
    public Observable<ResponseModel<HeartModel>> unheart(String albumId, String assetId) {
      return Chute.getHeartService().unheartObservable(albumId, assetId);
    }

    /**
     * Hearts a specific asset in an album.
     * <p>
     * The heart will be marked for the current user executing the requests.
     *
     * @param albumId The ID of the {@link AlbumModel} that holds the hearted asset.
     * @param assetId The ID of the {@link AssetModel} to be hearted.
     */
    public Observable<ResponseModel<HeartModel>> heart(String albumId, String assetId) {
      return Chute.getHeartService().heartObservable(albumId, assetId);
    }
  }

  public static class Calls {

    /**
     * Gets a number of all hearts associated with an asset in a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} that holds the hearted asset.
     * @param assetId The ID of the hearted {@link AssetModel}
     */
    public Call<ResponseModel<HeartModel>> heartCount(String albumId, String assetId) {
      return Chute.getHeartService().heartCountCall(albumId, assetId);
    }

    /**
     * Removes an existing heart from an asset.
     * <p>
     * The heart will be marked for the current user executing the requests.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the hearted asset.
     * @param assetId The ID of the hearted {@link AssetModel}
     */
    public Call<ResponseModel<HeartModel>> unheart(String albumId, String assetId) {
      return Chute.getHeartService().unheartCall(albumId, assetId);
    }

    /**
     * Hearts a specific asset in an album.
     * <p>
     * The heart will be marked for the current user executing the requests.
     *
     * @param albumId The ID of the {@link AlbumModel} that holds the hearted asset.
     * @param assetId The ID of the {@link AssetModel} to be hearted.
     */
    public Call<ResponseModel<HeartModel>> heart(String albumId, String assetId) {
      return Chute.getHeartService().heartCall(albumId, assetId);
    }
  }
}
