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
 * Neither the name of the Chute Corporation nor the names
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
package com.android.getchute.sdk.chutesdkandroid.model;

import android.text.TextUtils;

/**
 * The {@link PaginationModel} class wraps up information about pagination of
 * the response.
 * <p>
 * The response can show total pages, current page, previous page, first page,
 * last page and number of responses per page.
 */
public class PaginationModel {

  public static final int DEFAULT_PER_PAGE = 50;

  /**
   * The current page of the response.
   */
  private long currentPage;

  /**
   * The next page of the response.
   */
  private String nextPage;

  /**
   * The previous page of the response.
   */
  private String previousPage;

  /**
   * Number of responses per page.
   */
  private int perPage = DEFAULT_PER_PAGE;

  /**
   * Getters and setters.
   */
  public long getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(long currentPage) {
    this.currentPage = currentPage;
  }

  public String getNextPage() {
    return nextPage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  public boolean hasNextPage() {
    return !TextUtils.isEmpty(nextPage);
  }

  public String getPreviousPage() {
    return previousPage;
  }

  public void setPreviousPage(String previousPage) {
    this.previousPage = previousPage;
  }

  public boolean hasPreviousPage() {
    return !TextUtils.isEmpty(previousPage);
  }

  public int getPerPage() {
    return perPage;
  }

  public String getPerPageAsString() {
    return String.valueOf(perPage);
  }

  public void setPerPage(int perPage) {
    this.perPage = perPage;
  }

  @Override public String toString() {
    return "PaginationModel{" +
        "currentPage=" + currentPage +
        ", nextPage='" + nextPage + '\'' +
        ", previousPage='" + previousPage + '\'' +
        ", perPage=" + perPage +
        '}';
  }
}