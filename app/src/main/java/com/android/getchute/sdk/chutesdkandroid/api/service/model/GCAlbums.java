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

import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumBodyRequestModel;
import com.android.getchute.sdk.chutesdkandroid.retrofit.RetrofitService;
import io.reactivex.Observable;
import retrofit2.Call;

public class GCAlbums {

  public static class Observables {

    /**
     * Creates an album.
     * <p/>
     * Sending additional defined parameters will enable you to customize name,
     * privileges, visibility, moderation etc.
     *
     * @param name Name of the album
     * @param coverAssetId ID of the cover {@link AssetModel}
     */
    public static Observable<ResponseModel<AlbumModel>> createAlbum(String name,
        String coverAssetId) {
      AlbumBodyRequestModel albumBodyRequestModel = new AlbumBodyRequestModel();
      albumBodyRequestModel.setCoverAssetId(coverAssetId);
      albumBodyRequestModel.setName(name);
      return RetrofitService.get().getAlbumService().createAlbumObservable(albumBodyRequestModel);
    }

    /**
     * Deletes an album.
     * <p/>
     * Only users with appropriate permissions can delete albums.
     *
     * @param albumId The ID of the {@link AlbumModel} to be deleted.
     */
    public static Observable<ResponseModel<AlbumModel>> deleteAlbum(String albumId) {
      return RetrofitService.get().getAlbumService().deleteAlbumObservable(albumId);
    }

    /**
     * Retrieves details for a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose details are to be returned.
     */
    public static Observable<ResponseModel<AlbumModel>> getAlbum(String albumId) {
      return RetrofitService.get().getAlbumService().getAlbumObservable(albumId);
    }

    /**
     * Updates existing album.
     *
     * @param albumId The ID of the {@link AlbumModel} that needs to be updated.
     * @param coverAssetId ID of the cover {@link AssetModel}
     */
    public static Observable<ResponseModel<AlbumModel>> updateAlbum(String albumId,
        String coverAssetId,
        String name) {
      AlbumBodyRequestModel albumBodyRequestModel = new AlbumBodyRequestModel();
      albumBodyRequestModel.setCoverAssetId(coverAssetId);
      albumBodyRequestModel.setName(name);
      return RetrofitService.get().getAlbumService().updateAlbumObservable(albumId, albumBodyRequestModel);
    }

    /**
     * Pulls a complete list of all albums accessible to the user
     *
     * @param perPage Number of responses per page
     */
    public static Observable<ListResponseModel<AlbumModel>> listAlbums(String perPage) {
      return RetrofitService.get().getAlbumService().listAlbumsObservable(perPage);
    }

    /**
     * Pulls a complete list of all albums nested inside a specific album.
     *
     * @param albumId ID of parent {@link AlbumModel}
     */
    public static Observable<ListResponseModel<AlbumModel>> listNestedAlbums(String albumId) {
      return RetrofitService.get().getAlbumService().listNestedAlbumsObservable(albumId);
    }
  }

  public static class Calls {

    /**
     * Creates an album.
     * <p/>
     * Sending additional defined parameters will enable you to customize name,
     * privileges, visibility, moderation etc.
     *
     * @param name Name of the album
     * @param coverAssetId ID of the cover {@link AssetModel}
     */
    public static Call<ResponseModel<AlbumModel>> createAlbum(String name, String coverAssetId) {
      AlbumBodyRequestModel albumBodyRequestModel = new AlbumBodyRequestModel();
      albumBodyRequestModel.setCoverAssetId(coverAssetId);
      albumBodyRequestModel.setName(name);
      return RetrofitService.get().getAlbumService().createAlbumCall(albumBodyRequestModel);
    }

    /**
     * Deletes an album.
     * <p/>
     * Only users with appropriate permissions can delete albums.
     *
     * @param albumId The ID of the {@link AlbumModel} to be deleted.
     */
    public static Call<ResponseModel<AlbumModel>> deleteAlbum(String albumId) {
      return RetrofitService.get().getAlbumService().deleteAlbumCall(albumId);
    }

    /**
     * Retrieves details for a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose details are to be returned.
     */
    public static Call<ResponseModel<AlbumModel>> getAlbum(String albumId) {
      return RetrofitService.get().getAlbumService().getAlbumCall(albumId);
    }

    /**
     * Updates existing album.
     *
     * @param albumId The ID of the {@link AlbumModel} that needs to be updated.
     * @param coverAssetId ID of the cover {@link AssetModel}
     */
    public static Call<ResponseModel<AlbumModel>> updateAlbum(String albumId, String coverAssetId,
        String name) {
      AlbumBodyRequestModel albumBodyRequestModel = new AlbumBodyRequestModel();
      albumBodyRequestModel.setCoverAssetId(coverAssetId);
      albumBodyRequestModel.setName(name);
      return RetrofitService.get().getAlbumService().updateAlbumCall(albumId, albumBodyRequestModel);
    }

    /**
     * Pulls a complete list of all albums accessible to the user
     *
     * @param perPage Number of responses per page
     */
    public static Call<ListResponseModel<AlbumModel>> listAlbums(String perPage) {
      return RetrofitService.get().getAlbumService().listAlbumsCall(perPage);
    }

    /**
     * Pulls a complete list of all albums nested inside a specific album.
     *
     * @param albumId ID of parent {@link AlbumModel}
     */
    public static Call<ListResponseModel<AlbumModel>> listNestedAlbums(String albumId) {
      return RetrofitService.get().getAlbumService().listNestedAlbumsCall(albumId);
    }
  }
}
