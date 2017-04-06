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
package com.android.getchute.sdk.chutesdkandroid.api.service.vote;

import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.AssetModel;
import com.android.getchute.sdk.chutesdkandroid.model.VoteModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ResponseModel;
import com.android.getchute.sdk.chutesdkandroid.retrofit.RetrofitService;
import io.reactivex.Observable;
import retrofit2.Call;

public class GCVotes {

  public static class Observables {

    /**
     * Gets number of votes for a specific asset within an album.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the voted asset.
     * @param assetId The ID of the {@link AssetModel} whose votes are counted.
     */
    public static Observable<ResponseModel<VoteModel>> voteCount(String albumId, String assetId) {
      return RetrofitService.get().getVoteService().voteCountObservable(albumId, assetId);
    }

    /**
     * Deletes an existing heart from an asset.
     * <p>
     * The vote will be marked for the current user making the requests.
     *
     * @param albumId The ID of the {@link AlbumModel} containing asset whose vote is going to be
     * deleted.
     * @param assetId The ID of the {@link AssetModel} containing the vote to be deleted.
     */
    public static Observable<ResponseModel<Void>> unvote(String albumId, String assetId) {
      return RetrofitService.get().getVoteService().unvoteObservable(albumId, assetId);
    }

    /**
     * Creates a vote for a specific asset within an album.
     * <p>
     * The vote will be marked for the current user making the request.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the voted asset.
     * @param assetId The ID of the {@link AssetModel} containing the created vote.
     */
    public static Observable<ResponseModel<VoteModel>> vote(String albumId, String assetId) {
      return RetrofitService.get().getVoteService().voteObservable(albumId, assetId);
    }
  }

  public static class Calls {

    /**
     * Gets number of votes for a specific asset within an album.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the voted asset.
     * @param assetId The ID of the {@link AssetModel} whose votes are counted.
     */
    public static Call<ResponseModel<VoteModel>> voteCount(String albumId, String assetId) {
      return RetrofitService.get().getVoteService().voteCountCall(albumId, assetId);
    }

    /**
     * Deletes an existing heart from an asset.
     * <p>
     * The vote will be marked for the current user making the requests.
     *
     * @param albumId The ID of the {@link AlbumModel} containing asset whose vote is going to be
     * deleted.
     * @param assetId The ID of the {@link AssetModel} containing the vote to be deleted.
     */
    public static Call<ResponseModel<Void>> unvote(String albumId, String assetId) {
      return RetrofitService.get().getVoteService().unvoteCall(albumId, assetId);
    }

    /**
     * Creates a vote for a specific asset within an album.
     * <p>
     * The vote will be marked for the current user making the request.
     *
     * @param albumId The ID of the {@link AlbumModel} containing the voted asset.
     * @param assetId The ID of the {@link AssetModel} containing the created vote.
     */
    public static Call<ResponseModel<VoteModel>> vote(String albumId, String assetId) {
      return RetrofitService.get().getVoteService().voteCall(albumId, assetId);
    }
  }
}
