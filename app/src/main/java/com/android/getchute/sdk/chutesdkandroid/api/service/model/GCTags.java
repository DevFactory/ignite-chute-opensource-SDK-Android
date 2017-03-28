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

import android.text.TextUtils;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;
import com.android.getchute.sdk.chutesdkandroid.retrofit.RetrofitService;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;

public class GCTags {

  public static class Observables {

    /**
     * Adds more tags attached to an asset in an album.
     * <p>
     * The asset will not share the tags between albums.
     * <p>
     * Tags added will apply only to a specific asset in an album.
     *
     * @param albumId The ID of the {@link AlbumModel} containing asset that will hold the newly
     * created tags.
     * @param assetId The ID of the {@link AssetModel} containing the newly created tags.
     * @param tags List of tags to be added
     * <p> map key="tags" value={@link ArrayList <String>}</p>
     */
    public static Observable<ListResponseModel<String>> add(String albumId, String assetId,
        List<String> tags) {
      Map<String, List<String>> map = new HashMap<>();
      map.put("tags", tags);
      return RetrofitService.get().getTagService().addTagsObservable(albumId, assetId, map);
    }

    /**
     * Deletes tags from an asset by using tag names.
     * <p>
     * This request will modify the existing tag array attached to an asset.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset whose tags are about to
     * be deleted.
     * @param assetId The ID of the {@link AssetModel} whose tags are about to be deleted.
     * @param list List of tags for removal.
     * <p> ex. String tags=TextUtils.join(",", tagList);</p>
     */
    public static Observable<ListResponseModel<String>> remove(String albumId, String assetId,
        List<String> list) {
      String tags = TextUtils.join(",", list);
      return RetrofitService.get().getTagService().removeTagsObservable(albumId, assetId, tags);
    }

    /**
     * Pulls a complete list of all tags associated with an asset in a specific
     * album.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset with tags that are
     * about to be listed.
     * @param assetId The ID of the {@link AssetModel} containing the specific tags.
     */
    public static Observable<ListResponseModel<String>> get(String albumId, String assetId) {
      return RetrofitService.get().getTagService().getTagsObservable(albumId, assetId);
    }

    /**
     * Replaces all existing tags within an asset with a new set of tags.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset with tags that are
     * about to be updated.
     * @param assetId The ID of the {@link AssetModel} containing the specific tags.
     * @param tags List of new tags.
     * <p> map key="tags" value={@link ArrayList < String >}</p>
     */
    public static Observable<ListResponseModel<String>> replace(String albumId, String assetId,
        List<String> tags) {
      Map<String, List<String>> map = new HashMap<>();
      map.put("tags", tags);
      return RetrofitService.get().getTagService().replaceTagsObservable(albumId, assetId, map);
    }
  }

  public static class Calls {

    /**
     * Adds more tags attached to an asset in an album.
     * <p>
     * The asset will not share the tags between albums.
     * <p>
     * Tags added will apply only to a specific asset in an album.
     *
     * @param albumId The ID of the {@link AlbumModel} containing asset that will hold the newly
     * created tags.
     * @param assetId The ID of the {@link AssetModel} containing the newly created tags.
     * @param tags List of tags to be added
     * <p> map key="tags" value={@link ArrayList <String>}</p>
     */
    public static Call<ListResponseModel<String>> add(String albumId, String assetId,
        List<String> tags) {
      Map<String, List<String>> map = new HashMap<>();
      map.put("tags", tags);
      return RetrofitService.get().getTagService().addTagsCall(albumId, assetId, map);
    }

    /**
     * Deletes tags from an asset by using tag names.
     * <p>
     * This request will modify the existing tag array attached to an asset.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset whose tags are about to
     * be deleted.
     * @param assetId The ID of the {@link AssetModel} whose tags are about to be deleted.
     * @param list List of tags for removal.
     * <p> ex. String tags=TextUtils.join(",", tagList);</p>
     */
    public static Call<ListResponseModel<String>> remove(String albumId, String assetId,
        List<String> list) {
      String tags = TextUtils.join(",", list);
      return RetrofitService.get().getTagService().removeTagsCall(albumId, assetId, tags);
    }

    /**
     * Pulls a complete list of all tags associated with an asset in a specific
     * album.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset with tags that are
     * about to be listed.
     * @param assetId The ID of the {@link AssetModel} containing the specific tags.
     */
    public static Call<ListResponseModel<String>> get(String albumId, String assetId) {
      return RetrofitService.get().getTagService().getTagsCall(albumId, assetId);
    }

    /**
     * Replaces all existing tags within an asset with a new set of tags.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the asset with tags that are
     * about  to be updated.
     * @param assetId The ID of the {@link AssetModel} containing the specific tags.
     * @param tags List of new tags.
     * <p> map key="tags" value={@link ArrayList < String >}</p>
     */
    public static Call<ListResponseModel<String>> replace(String albumId, String assetId,
        List<String> tags) {
      Map<String, List<String>> map = new HashMap<>();
      map.put("tags", tags);
      return RetrofitService.get().getTagService().replaceTagsCall(albumId, assetId, map);
    }
  }
}
