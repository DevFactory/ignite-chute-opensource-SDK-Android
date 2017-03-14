package com.android.getchute.sdk.chutesdkandroid.api.service.model;

import com.android.getchute.sdk.chutesdkandroid.api.Chute;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AlbumBodyRequestModel;
import retrofit2.Call;
import rx.Observable;

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
      return Chute.getAlbumService().createAlbumObservable(albumBodyRequestModel);
    }

    /**
     * Deletes an album.
     * <p/>
     * Only users with appropriate permissions can delete albums.
     *
     * @param albumId The ID of the {@link AlbumModel} to be deleted.
     */
    public static Observable<ResponseModel<AlbumModel>> deleteAlbum(String albumId) {
      return Chute.getAlbumService().deleteAlbumObservable(albumId);
    }

    /**
     * Retrieves details for a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose details are to be returned.
     */
    public static Observable<ResponseModel<AlbumModel>> getAlbum(String albumId) {
      return Chute.getAlbumService().getAlbumObservable(albumId);
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
      return Chute.getAlbumService().updateAlbumObservable(albumId, albumBodyRequestModel);
    }

    /**
     * Pulls a complete list of all albums accessible to the user
     *
     * @param perPage Number of responses per page
     */
    public static Observable<ListResponseModel<AlbumModel>> listAlbums(String perPage) {
      return Chute.getAlbumService().listAlbumsObservable(perPage);
    }

    /**
     * Pulls a complete list of all albums nested inside a specific album.
     *
     * @param albumId ID of parent {@link AlbumModel}
     */
    public static Observable<ListResponseModel<AlbumModel>> listNestedAlbums(String albumId) {
      return Chute.getAlbumService().listNestedAlbumsObservable(albumId);
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
      return Chute.getAlbumService().createAlbumCall(albumBodyRequestModel);
    }

    /**
     * Deletes an album.
     * <p/>
     * Only users with appropriate permissions can delete albums.
     *
     * @param albumId The ID of the {@link AlbumModel} to be deleted.
     */
    public static Call<ResponseModel<AlbumModel>> deleteAlbum(String albumId) {
      return Chute.getAlbumService().deleteAlbumCall(albumId);
    }

    /**
     * Retrieves details for a specific album.
     *
     * @param albumId The ID of the {@link AlbumModel} whose details are to be returned.
     */
    public static Call<ResponseModel<AlbumModel>> getAlbum(String albumId) {
      return Chute.getAlbumService().getAlbumCall(albumId);
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
      return Chute.getAlbumService().updateAlbumCall(albumId, albumBodyRequestModel);
    }

    /**
     * Pulls a complete list of all albums accessible to the user
     *
     * @param perPage Number of responses per page
     */
    public static Call<ListResponseModel<AlbumModel>> listAlbums(String perPage) {
      return Chute.getAlbumService().listAlbumsCall(perPage);
    }

    /**
     * Pulls a complete list of all albums nested inside a specific album.
     *
     * @param albumId ID of parent {@link AlbumModel}
     */
    public static Call<ListResponseModel<AlbumModel>> listNestedAlbums(String albumId) {
      return Chute.getAlbumService().listNestedAlbumsCall(albumId);
    }
  }
}
