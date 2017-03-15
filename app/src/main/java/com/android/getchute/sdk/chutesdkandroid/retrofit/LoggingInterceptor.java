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
package com.android.getchute.sdk.chutesdkandroid.retrofit;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {

  public static final String TAG = LoggingInterceptor.class.getSimpleName();

  @Override public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    long t1 = System.nanoTime();
    String requestLog = String.format("Sending request %s on %s%n%s",
        request.url(), chain.connection(), request.headers());
    Log.d(TAG, String.format("Sending request %s on %s%n%s",
        request.url(), chain.connection(), request.headers()));
    if (request.method().compareToIgnoreCase("post") == 0) {
      requestLog = "\n" + requestLog + "\n" + bodyToString(request);
    }
    Log.d(TAG, "request" + "\n" + requestLog);

    Response response = chain.proceed(request);
    long t2 = System.nanoTime();

    String responseLog = String.format("Received response for %s in %.1fms%n%s",
        response.request().url(), (t2 - t1) / 1e6d, response.headers());
    String bodyString = response.body().string();
    Log.d(TAG, "response" + "\n" + responseLog + "\n" + bodyString);

    return response.newBuilder()
        .body(ResponseBody.create(response.body().contentType(), bodyString))
        .build();
  }

  public static String bodyToString(final Request request) {
    try {
      final Request copy = request.newBuilder().build();
      final Buffer buffer = new Buffer();
      copy.body().writeTo(buffer);
      return buffer.readUtf8();
    } catch (final IOException e) {
      return "Did not work";
    }
  }
}