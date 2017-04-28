package com.android.getchute.sdk.chutesdkandroid.api.tags;

import com.android.getchute.sdk.chutesdkandroid.Constants;
import com.android.getchute.sdk.chutesdkandroid.api.service.tag.TagService;
import com.android.getchute.sdk.chutesdkandroid.model.TagModelGenerator;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;

public class MockTagService implements TagService {

  private final BehaviorDelegate<TagService> delegate;

  public MockTagService(BehaviorDelegate<TagService> service) {
    this.delegate = service;
  }

  @Override
  public Observable<ListResponseModel<String>> addTagsObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Body Map<String, List<String>> tags) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_POST_RESPONSE_OK);
    return delegate.returningResponse(response).addTagsObservable(albumId, assetId, tags);
  }

  @Override public Observable<ListResponseModel<String>> removeTagsObservable(
      @Path("album_id") String albumId, @Path("asset_id") String assetId,
      @Path("tags") String tags) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_DELETE_RESPONSE_OK);
    return delegate.returningResponse(response).removeTagsObservable(albumId, assetId, tags);
  }

  @Override
  public Observable<ListResponseModel<String>> getTagsObservable(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_GET_RESPONSE_SUCESS);
    return delegate.returningResponse(response).getTagsObservable(albumId, assetId);
  }

  @Override public Observable<ListResponseModel<String>> replaceTagsObservable(
      @Path("album_id") String albumId, @Path("asset_id") String assetId,
      @Body Map<String, List<String>> tags) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_REPLACE_RESPONSE_OK);
    return delegate.returningResponse(response).addTagsObservable(albumId, assetId, tags);
  }

  @Override public Call<ListResponseModel<String>> addTagsCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Body Map<String, List<String>> tags) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_POST_RESPONSE_OK);
    return delegate.returningResponse(response).addTagsCall(albumId, assetId, tags);
  }

  @Override public Call<ListResponseModel<String>> removeTagsCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Path("tags") String tags) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_DELETE_RESPONSE_OK);
    return delegate.returningResponse(response).removeTagsCall(albumId, assetId, tags);
  }

  @Override public Call<ListResponseModel<String>> getTagsCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_GET_RESPONSE_SUCESS);
    return delegate.returningResponse(response).getTagsCall(albumId, assetId);
  }

  @Override public Call<ListResponseModel<String>> replaceTagsCall(@Path("album_id") String albumId,
      @Path("asset_id") String assetId, @Body Map<String, List<String>> tags) {
    ListResponseModel<String> response =
        TagModelGenerator.getListResponseModel(Constants.FilePaths.Tag.TAG_REPLACE_RESPONSE_OK);
    return delegate.returningResponse(response).addTagsCall(albumId, assetId, tags);
  }
}
