package com.android.getchute.sdk.chutesdkandroid.api.asset;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.service.asset.AssetService;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.ModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.model.body.AssetRequestModel;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import retrofit2.mock.BehaviorDelegate;

public class MockAssetService implements AssetService {

  private final BehaviorDelegate<AssetService> delegate;

  public MockAssetService(BehaviorDelegate<AssetService> service) {
    this.delegate = service;
  }

  @Override
  public Observable<ResponseModel<AssetModel>> getObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<AssetModel> response =
        ModelGenerator.Asset.getResponseModel(Constants.FilePaths.Asset.GET_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getObservable(albumId, assetId);
  }

  @Override public Observable<ListResponseModel<AssetModel>> listObservable(@Path("id") String id,
      @Query("per_page") String perPage, @Query("type") String type,
      @Query("username") String username, @Query("tags") List<String> tags,
      @Query("service") String service) {
    ListResponseModel<AssetModel> response =
        ModelGenerator.Asset.getListResponseModel(Constants.FilePaths.Asset.LIST_RESPONSE_SUCCESS);
    return delegate.returningResponse(response)
        .listObservable(id, perPage, type, username, tags, service);
  }

  @Override
  public Observable<ListResponseModel<AssetModel>> getNextPageObservable(@Url String url) {
    ListResponseModel<AssetModel> response =
        ModelGenerator.Asset.getListResponseModel(
            Constants.FilePaths.Asset.NEXT_PAGE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getNextPageObservable(url);
  }

  @Override
  public Observable<ResponseModel<Void>> deleteObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<Void> response =
        ModelGenerator.getEmptyResponseModel(Constants.FilePaths.Asset.DELETE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).deleteObservable(albumId, assetId);
  }

  @Override
  public Observable<ResponseModel<AssetModel>> copyObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId) {
    ResponseModel<AssetModel> response =
        ModelGenerator.Asset.getResponseModel(Constants.FilePaths.Asset.COPY_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).moveObservable(albumId, assetId, newAlbumId);
  }

  @Override
  public Observable<ResponseModel<AssetModel>> moveObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId) {
    ResponseModel<AssetModel> response =
        ModelGenerator.Asset.getResponseModel(Constants.FilePaths.Asset.MOVE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).moveObservable(albumId, assetId, newAlbumId);
  }

  @Override public Observable<ListResponseModel<AssetModel>> importObservable(
      @Path("album_id") String albumId, @Body AssetRequestModel body) {
    List<String> urls = new ArrayList<>();
    urls.add(Constants.TEST_IMAGE_URL);
    AssetRequestModel assetRequestModel = new AssetRequestModel();
    assetRequestModel.setUrls(urls);
    ListResponseModel<AssetModel> response =
        ModelGenerator.Asset.getListResponseModel(
            Constants.FilePaths.Asset.IMPORT_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).importObservable(albumId, assetRequestModel);
  }

  @Override public Observable<ResponseModel<HashMap<String, String>>> exifObservable(
      @Path("album_id") String albumId, @Path("asset_id") String assetId) {
    ResponseModel<HashMap<String, String>> response =
        ModelGenerator.Asset.getExifResponseModel(Constants.FilePaths.Asset.EXIF_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).exifObservable(albumId, assetId);
  }

  /** Calls **/

  @Override public Call<ResponseModel<AssetModel>> getCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<AssetModel> response =
        ModelGenerator.Asset.getResponseModel(Constants.FilePaths.Asset.GET_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getCall(albumId, assetId);
  }

  @Override public Call<ListResponseModel<AssetModel>> listCall(@Path("id") String id,
      @Query("per_page") String perPage, @Query("type") String type,
      @Query("username") String username, @Query("tags") List<String> tags,
      @Query("service") String service) {
    ListResponseModel<AssetModel> response =
        ModelGenerator.Asset.getListResponseModel(Constants.FilePaths.Asset.LIST_RESPONSE_SUCCESS);
    return delegate.returningResponse(response)
        .listCall(id, perPage, type, username, tags, service);
  }

  @Override public Call<ListResponseModel<AssetModel>> getNextPageCall(@Url String url) {
    ListResponseModel<AssetModel> response =
        ModelGenerator.Asset.getListResponseModel(
            Constants.FilePaths.Asset.NEXT_PAGE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).getNextPageCall(url);
  }

  @Override public Call<ResponseModel<Void>> deleteCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<Void> response =
        ModelGenerator.getEmptyResponseModel(Constants.FilePaths.Asset.DELETE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).deleteCall(albumId, assetId);
  }

  @Override public Call<ResponseModel<AssetModel>> copyCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId) {
    ResponseModel<AssetModel> response =
        ModelGenerator.Asset.getResponseModel(Constants.FilePaths.Asset.COPY_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).moveCall(albumId, assetId, newAlbumId);
  }

  @Override public Call<ResponseModel<AssetModel>> moveCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("new_album_id") String newAlbumId) {
    ResponseModel<AssetModel> response =
        ModelGenerator.Asset.getResponseModel(Constants.FilePaths.Asset.MOVE_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).moveCall(albumId, assetId, newAlbumId);
  }

  @Override public Call<ListResponseModel<AssetModel>> importCall(@Path("album_id") String albumId,
      @Body AssetRequestModel body) {
    List<String> urls = new ArrayList<>();
    urls.add(Constants.TEST_IMAGE_URL);
    AssetRequestModel assetRequestModel = new AssetRequestModel();
    assetRequestModel.setUrls(urls);
    ListResponseModel<AssetModel> response =
        ModelGenerator.Asset.getListResponseModel(
            Constants.FilePaths.Asset.IMPORT_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).importCall(albumId, assetRequestModel);
  }

  @Override
  public Call<ResponseModel<HashMap<String, String>>> exifCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ResponseModel<HashMap<String, String>> response =
        ModelGenerator.Asset.getExifResponseModel(Constants.FilePaths.Asset.EXIF_RESPONSE_SUCCESS);
    return delegate.returningResponse(response).exifCall(albumId, assetId);
  }
}
