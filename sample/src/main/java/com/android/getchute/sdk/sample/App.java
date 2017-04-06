package com.android.getchute.sdk.sample;

import android.app.Application;

import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;

public class App extends Application {

    public static final String CLIENT_ID = "YOUR_CLIENT_ID";
    public static final String CLIENT_SECRET =
            "YOUR_CLIENT_SECRET";

    @Override
    public void onCreate() {
        super.onCreate();

        TokenAuthenticationProvider.init(getApplicationContext());
    }
}
