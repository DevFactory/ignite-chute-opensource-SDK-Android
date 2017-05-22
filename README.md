CHUTE-SDK-ANDROID
==

This is the official Android SDK for [www.getchute.com](www.getchute.com) API.

The Chute Android SDK allows you to easily manage your Chute albums and assets.

Download
===

Add jcenter to your list of Maven repositories, if needed.

```
allprojects {
    repositories {
        jcenter()
    }
}
```

Then, add the SDK as a project dependency.

```
dependencies {
    compile 'com.chute.sdk.android:sdk:1.0.0'
  }
```

Usage
===

The SDK uses [rxjava2](https://github.com/ReactiveX/RxAndroid) Observables and [retrofit](https://github.com/square/retrofit) Calls for creating authentication, album, asset, heart, vote, tag and user requests.
  
All developers need to register their application before getting started at [http://apps.getchute.com](http://apps.getchute.com). A registered OAuth application is assigned a unique Client ID and Client Secret, which are used to acquire access tokens for users.

Once you have acquired an access token for a user, you should include that token in each request a user makes. 
In order to include the token in each request, you need to initialize <code>TokenAuthenticationProvider</code> in your Application class. 
```
  @Override
      public void onCreate() {
          super.onCreate();
          TokenAuthenticationProvider.init(getApplicationContext());
      }
  ```

Once you retrieve the token, you can easily save it using the <code>TokenAuthenticationProvider</code>:

```
 TokenAuthenticationProvider.getInstance().setToken(YOUR_TOKEN);
```
 
 You can either use Observables for getting a response:
 
 ```
 Observable<ListResponseModel<AlbumModel>> albumObservable = GCAlbums.Observables.list(PER_PAGE);
         albumObservable.observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.io())
                 .subscribe(new DisposableObserver<ListResponseModel<AlbumModel>>() {
                     @Override
                     public void onNext(ListResponseModel<AlbumModel> value) {
                         Log.d(TAG, "Album response: " + value.toString());
                     }
 
                     @Override
                     public void onError(Throwable e) {
                         Log.e(TAG, "An error occurred: " + e.getMessage());
                     }
 
                     @Override
                     public void onComplete() {
 
                     }
                 });
 ```
 
Or Calls:

 ```
 Call<ListResponseModel<AlbumModel>> albumCall = GCAlbums.Calls.list(PER_PAGE);
         albumCall.enqueue(new Callback<ListResponseModel<AlbumModel>>() {
             @Override
             public void onResponse(Call<ListResponseModel<AlbumModel>> call, Response<ListResponseModel<AlbumModel>> response) {
                 Log.d(TAG, "Album response: " + response.toString());
             }
 
             @Override
             public void onFailure(Call<ListResponseModel<AlbumModel>> call, Throwable t) {
                 Log.e(TAG, "An error occurred: " + t.getMessage());
             }
         });
 ```

For additional information see the [documentation](http://www.getchute.com/developers/reference/#).

License
==

Chute SDK is released under the MIT licence. See [LICENSE](LICENSE) for details.