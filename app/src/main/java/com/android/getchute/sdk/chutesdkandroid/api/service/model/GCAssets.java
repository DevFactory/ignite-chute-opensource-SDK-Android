/**
 * Copyright (c) 2011, Chute Corporation. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * <p>
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the Chute Corporation nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * <p>
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
package com.android.getchute.sdk.chutesdkandroid.api.service.model;

import android.support.annotation.Nullable;
import com.android.getchute.sdk.chutesdkandroid.api.Chute;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.PaginationModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AssetBodyRequestModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AssetRequestBody;
import com.android.getchute.sdk.chutesdkandroid.model.enums.AccountType;
import com.android.getchute.sdk.chutesdkandroid.model.enums.AssetType;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import rx.Observable;

public class GCAssets {

  public static class Observables {

    /**
     * Gets list of assets from a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose assets are being retrieved.
     * @param perPage Number of assets per page.
     */
    public Observable<ListResponseModel<AssetModel>> list(String albumId, String perPage,
        @Nullable AssetType type, @Nullable String username, @Nullable List<String> tags,
        @Nullable AccountType service) {
      return Chute.getAssetService()
          .listObservable(albumId, perPage, type.name(), username, tags, service.getLoginMethod());
    }

    /**
     * Gets a specific asset from a given album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose asset is demanded.
     * @param assetId The ID of the requested {@link AssetModel}
     */
    public Observable<ResponseModel<AssetModel>> get(String albumId, String assetId) {
      return Chute.getAssetService().getObservable(albumId, assetId);
    }

    /**
     * Copies an asset from one album to another.
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be copied.
     * @param assetId The ID of the {@link AssetModel} you wish to copy to another album
     * @param newAlbumId The ID of the {@link AlbumModel} that is going to store the copied asset.
     */
    public Observable<ResponseModel<AssetModel>> copy(String albumId, String assetId,
        String newAlbumId) {
      return Chute.getAssetService().copyObservable(albumId, assetId, newAlbumId);
    }

    /**
     * Deletes an asset from album
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be deleted.
     * @param assetId The ID of the {@link AssetModel} to be removed.
     */
    public Observable<ResponseModel<AssetModel>> delete(String albumId, String assetId) {
      return Chute.getAssetService().deleteObservable(albumId, assetId);
    }

    /**
     * Moves the specified asset from one album to another.
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be moved.
     * @param assetId The ID of the {@link AssetModel} you wish to move to another album.
     * @param newAlbumId The ID of the {@link AlbumModel} that is going to store the moved asset.
     */
    public Observable<ResponseModel<AssetModel>> move(String albumId, String assetId,
        String newAlbumId) {
      return Chute.getAssetService().moveObservable(albumId, assetId, newAlbumId);
    }

    /**
     * Imports assets to an album.
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be imported.
     * @param assetId The ID of the {@link AssetModel} to import assets into.
     * @param urls List of URL-s you want to import.
     * @param instagramIds List of Instagram IDs you want to import.
     */
    public Observable<ResponseModel<AssetModel>> importAssets(String albumId, String assetId,
        @Nullable List<String> urls, @Nullable List<String> instagramIds) {
      return Chute.getAssetService().importObservable(albumId, assetId, urls, instagramIds);
    }

    /**
     * Updates the caption and/or tags of an asset.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset to be updated.
     * @param assetId The ID of the {@link AssetModel} that needs to be updated.
     */
    public Observable<ResponseModel<AssetModel>> update(String albumId, String assetId,
        List<String> tags) {
      AssetRequestBody assetRequestBody = new AssetRequestBody();
      AssetBodyRequestModel assetBodyRequestModel = new AssetBodyRequestModel();
      assetBodyRequestModel.setTags(tags);
      assetRequestBody.setAsset(assetBodyRequestModel);
      return Chute.getAssetService().updateObservable(albumId, assetId, assetRequestBody);
    }

    /**
     * Gets exif info for an asset.
     * <p>
     * Empty if there are no available exif parameters.
     *
     * @param albumId The ID of the {@link AlbumModel} that holds the asset whose exif data needs
     * to
     * be retrieved.
     * @param assetId The ID of the {@link AssetModel} containing exif data to be retrieved.
     */
    public Observable<ResponseModel<HashMap<String, String>>> exif(String albumId,
        String assetId) {
      return Chute.getAssetService().exifObservable(albumId, assetId);
    }

    /**
     * @param paginationModel {@link PaginationModel} that holds the next page of assets URL
     */
    public static Observable<ListResponseModel<AssetModel>> getNextPage(
        PaginationModel paginationModel) {
      return Chute.getAssetService().getNextPageObservable(paginationModel.getNextPage());
    }
  }

  public static class Calls {

    /**
     * Gets list of assets from a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose assets are being retrieved.
     * @param perPage Number of assets per page.
     */
    public Call<ListResponseModel<AssetModel>> list(String albumId, String perPage,
        @Nullable AssetType type, @Nullable String username, @Nullable List<String> tags,
        @Nullable AccountType service) {
      return Chute.getAssetService()
          .listCall(albumId, perPage, type.name(), username, tags, service.getLoginMethod());
    }

    /**
     * Gets a specific asset from a given album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose asset is demanded.
     * @param assetId The ID of the requested {@link AssetModel}
     */
    public Call<ResponseModel<AssetModel>> get(String albumId, String assetId) {
      return Chute.getAssetService().getCall(albumId, assetId);
    }

    /**
     * Copies an asset from one album to another.
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be copied.
     * @param assetId The ID of the {@link AssetModel} you wish to copy to another album
     * @param newAlbumId The ID of the {@link AlbumModel} that is going to store the copied asset.
     */
    public Call<ResponseModel<AssetModel>> copy(String albumId, String assetId,
        String newAlbumId) {
      return Chute.getAssetService().copyCall(albumId, assetId, newAlbumId);
    }

    /**
     * Imports assets to an album.
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be imported.
     * @param assetId The ID of the {@link AssetModel} to import assets into.
     * @param urls List of URL-s you want to import.
     * @param instagramIds List of Instagram IDs you want to import.
     */
    public Call<ResponseModel<AssetModel>> importAssets(String albumId, String assetId,
        @Nullable List<String> urls, @Nullable List<String> instagramIds) {
      return Chute.getAssetService().importCall(albumId, assetId, urls, instagramIds);
    }

    /**
     * Deletes an asset from album
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be deleted.
     * @param assetId The ID of the {@link AssetModel} to be removed.
     */
    public Call<ResponseModel<AssetModel>> delete(String albumId, String assetId) {
      return Chute.getAssetService().deleteCall(albumId, assetId);
    }

    /**
     * Moves the specified asset from one album to another.
     *
     * @param albumId The ID of the {@link AlbumModel} holding the asset to be moved.
     * @param assetId The ID of the {@link AssetModel} you wish to move to another album.
     * @param newAlbumId The ID of the {@link AlbumModel} that is going to store the moved asset.
     */
    public Call<ResponseModel<AssetModel>> move(String albumId, String assetId,
        String newAlbumId) {
      return Chute.getAssetService().moveCall(albumId, assetId, newAlbumId);
    }

    /**
     * Updates the caption and/or tags of an asset.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset to be updated.
     * @param assetId The ID of the {@link AssetModel} that needs to be updated.
     */
    public Call<ResponseModel<AssetModel>> update(String albumId, String assetId,
        List<String> tags) {
      AssetRequestBody assetRequestBody = new AssetRequestBody();
      AssetBodyRequestModel assetBodyRequestModel = new AssetBodyRequestModel();
      assetBodyRequestModel.setTags(tags);
      assetRequestBody.setAsset(assetBodyRequestModel);
      return Chute.getAssetService().updateCall(albumId, assetId, assetRequestBody);
    }

    /**
     * Gets exif info for an asset.
     * <p>
     * Empty if there are no available exif parameters.
     *
     * @param albumId The ID of the {@link AlbumModel} that holds the asset whose exif data needs
     * to
     * be retrieved.
     * @param assetId The ID of the {@link AssetModel} containing exif data to be retrieved.
     */
    public Call<ResponseModel<HashMap<String, String>>> exif(String albumId,
        String assetId) {
      return Chute.getAssetService().exifCall(albumId, assetId);
    }

    /**
     * @param paginationModel {@link PaginationModel} that holds the next page of assets URL
     */
    public static Call<ListResponseModel<AssetModel>> getNextPage(
        PaginationModel paginationModel) {
      return Chute.getAssetService().getNextPageCall(paginationModel.getNextPage());
    }
  }
}
