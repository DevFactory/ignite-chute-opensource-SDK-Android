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
package com.android.getchute.sdk.chutesdkandroid;

public class Constants {

  public static final String DEFAULT_PER_PAGE = "50";
  public static final String TEST_IMAGE_URL = "https://goo.gl/Q5MaKv";
  public static final String MOCK_OAUTH_TOKEN = "1234567890";
  public static final String MOCK_ERROR_TOKEN = "99976f13";

  public static class FilePaths {

    public static class Login {

      public static final String RESPONSE_SUCCESS = "login/auth_response_ok.json";
      public static final String RESPONSE_FAIL = "login/auth_response_error.json";
      public static final String REQUEST_SUCCESS = "login/auth_request_ok.json";
      public static final String REQUEST_FAIL = "login/auth_request_error.json";
    }

    public static class Album {

      public static final String LIST_RESPONSE_SUCCESS = "albums/album_list_response_ok.json";
      public static final String LIST_RESPONSE_FAIL_MISSING_TOKEN =
          "albums/album_list_response_error.json";
      public static final String GET_RESPONSE_SUCCESS = "albums/album_get_response_ok.json";
      public static final String GET_RESPONSE_FAIL_NONEXISTENT_ALBUM =
          "albums/album_get_response_error.json";
      public static final String CREATE_RESPONSE_SUCCESS =
          "albums/album_create_response_ok.json";
      public static final String CREATE_RESPONSE_FAIL_WRONG_TOKEN =
          "albums/album_create_response_error.json";
      public static final String CREATE_REQUEST_BODY_NAME =
          "albums/album_create_request_ok.json";
      public static final String CREATE_REQUEST_BODY_PARENT_ID =
          "albums/album_create_request_error.json";
      public static final String UPDATE_RESPONSE_SUCCESS =
          "albums/album_update_response_ok.json";
      public static final String UPDATE_RESPONSE_FAIL_MISSING_TOKEN =
          "albums/album_update_response_error.json";
      public static final String UPDATE_REQUEST_SUCCESS =
          "albums/album_create_request_ok.json";
      public static final String UPDATE_REQUEST_BODY_WRONG_PARAMETER =
          "albums/album_update_request_error.json";
      public static final String NESTED_RESPONSE_SUCCESS =
          "albums/album_nested_response_ok.json";
      public static final String NESTED_RESPONSE_FAIL_NONEXISTENT_ALBUM =
          "albums/album_nested_response_error.json";
      public static final String DELETE_RESPONSE_SUCCESS =
          "albums/album_delete_response_ok.json";
      public static final String DELETE_RESPONSE_FAIL_NONEXISTENT_ALBUM =
          "albums/album_delete_response_error.json";
      public static final String NEXT_PAGE_RESPONSE_SUCCESS =
          "albums/album_next_page_response_ok.json";
      public static final String NEXT_PAGE_RESPONSE_MISSING_TOKEN =
          "albums/album_next_page_response_error.json";
    }

    public static class Asset {

      public static final String LIST_RESPONSE_SUCCESS = "assets/asset_list_response_ok.json";
      public static final String LIST_RESPONSE_FAIL_MISSING_TOKEN =
          "assets/asset_list_response_error.json";
      public static final String GET_RESPONSE_SUCCESS = "assets/asset_get_response_ok.json";
      public static final String GET_RESPONSE_FAIL_ALBUM_NOT_FOUND =
          "assets/asset_get_response_error.json";
      public static final String IMPORT_RESPONSE_SUCCESS = "assets/asset_import_response_ok.json";
      public static final String IMPORT_RESPONSE_FAIL_WRONG_TOKEN =
          "assets/asset_import_response_error.json";
      public static final String MOVE_RESPONSE_SUCCESS = "assets/asset_move_response_ok.json";
      public static final String MOVE_RESPONSE_FAIL_WRONG_TOKEN =
          "assets/asset_move_response_error.json";
      public static final String COPY_RESPONSE_SUCCESS = "assets/asset_copy_response_ok.json";
      public static final String COPY_RESPONSE_FAIL_MISSING_TOKEN =
          "assets/asset_copy_response_error.json";
      public static final String EXIF_RESPONSE_SUCCESS = "assets/asset_exif_response_ok.json";
      public static final String EXIF_RESPONSE_FAIL_NONEXISTENT_ASSET =
          "assets/asset_exif_response_error.json";
      public static final String NEXT_PAGE_RESPONSE_SUCCESS =
          "assets/asset_next_page_response_ok.json";
      public static final String NEXT_PAGE_RESPONSE_FAIL_MISSING_TOKEN =
          "assets/asset_next_page_response_error.json";
      public static final String DELETE_RESPONSE_SUCCESS = "assets/asset_delete_response_ok.json";
      public static final String DELETE_RESPONSE_FAIL_ASSET_NOT_FOUND =
          "assets/asset_delete_response_error.json";
    }

    public static class Heart {

      public static final String HEART_GET_RESPONSE_SUCCESS = "hearts/heart_get_response_ok.json";
      public static final String HEART_GET_RESPONSE_FAIL_ASSET_NOT_FOUND =
          "hearts/heart_get_response_error.json";
      public static final String HEART_POST_RESPONSE_SUCCESS = "hearts/heart_post_response_ok.json";
      public static final String HEART_POST_RESPONSE_FAIL_ASSET_NOT_FOUND =
          "hearts/heart_post_response_error.json";
      public static final String HEART_REMOVE_RESPONSE_SUCCESS =
          "hearts/heart_remove_response_ok.json";
      public static final String HEART_REMOVE_RESPONSE_FAIL =
          "hearts/heart_remove_response_error.json";
    }

    public static class Vote {

      public static final String VOTE_GET_RESPONSE_SUCCESS = "votes/vote_get_response_ok.json";
      public static final String VOTE_GET_RESPONSE_FAIL_ASSET_NOT_FOUND =
          "votes/vote_get_response_error.json";
      public static final String VOTE_POST_RESPONSE_SUCCESS = "votes/vote_post_response_ok.json";
      public static final String VOTE_POST_RESPONSE_FAIL_ASSET_NOT_FOUND =
          "votes/vote_post_response_error.json";
      public static final String VOTE_REMOVE_RESPONSE_SUCCESS =
          "votes/vote_remove_response_ok.json";
      public static final String VOTE_REMOVE_RESPONSE_FAIL =
          "votes/vote_remove_response_error.json";
    }

    public static class Tag {

      public static final String TAG_GET_RESPONSE_SUCESS = "tags/tag_get_response_ok.json";
      public static final String TAG_GET_RESPONSE_ASSET_NOT_FOUND =
          "tags/tag_get_response_error.json";
      public static final String TAG_POST_REQUEST_BODY = "tags/tag_post_request_ok.json";
      public static final String TAG_POST_RESPONSE_OK = "tags/tag_post_response_ok.json";
      public static final String TAG_POST_RESPONSE_MISSING_TOKEN =
          "tags/tag_post_response_error.json";
      public static final String TAG_REPLACE_RESPONSE_OK = "tags/tag_replace_response_ok.json";
      public static final String TAG_REPLACE_RESPONSE_MISSING_TOKEN =
          "tags/tag_replace_response_error.json";
      public static final String TAG_DELETE_RESPONSE_OK = "tags/tag_remove_response_ok.json";
      public static final String TAG_DELETE_RESPONSE_MISSING_TOKEN =
          "tags/tag_remove_response_error.json";
    }

    public static class User {

      public static final String USER_CURRENT_RESPONSE_SUCCESS =
          "users/user_get_current_response_ok.json";
      public static final String USER_CURRENT_RESPONSE_FAIL_MISSING_TOKEN =
          "users/user_get_current_response_error.json";
      public static final String USER_CREATE_RESPONSE_SUCCESS =
          "users/user_create_response_ok.json";
      public static final String USER_CREATE_RESPONSE_FAIL_EMAIL_ALREADY_TAKEN =
          "users/user_create_response_error.json";
      public static final String USER_CREATE_REQUEST_SUCCESS = "users/user_create_request_ok.json";
      public static final String USER_CREATE_REQUEST_FAIL = "users/user_create_request_error.json";
      public static final String USER_GET_RESPONSE_OK = "users/user_get_response_ok.json";
      public static final String USER_GET_RESPONSE_FAIL_USER_NOT_FOUND =
          "users/user_get_response_error.json";
      public static final String USER_UPDATE_RESPONSE_SUCCESS =
          "users/user_update_response_ok.json";
      public static final String USER_UPDATE_RESPONSE_FAIL_MISSING_TOKEN =
          "users/user_update_response_error.json";
      public static final String USER_UPDATE_REQUEST = "users/user_update_request_ok.json";
    }
  }
}
