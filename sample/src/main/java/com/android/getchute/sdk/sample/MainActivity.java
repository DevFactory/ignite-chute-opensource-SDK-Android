package com.android.getchute.sdk.sample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.getchute.sdk.chutesdkandroid.api.service.album.GCAlbums;
import com.android.getchute.sdk.chutesdkandroid.model.AlbumModel;
import com.android.getchute.sdk.chutesdkandroid.model.base.response.ListResponseModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PER_PAGE = "50";
    @Bind(R.id.root)
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonExecuteRequest)
    public void executeRequest() {
        getAlbumsUsingObservable();
        //or
//         getAlbumsUsingCall();

    }

    private void getAlbumsUsingObservable() {
        Observable<ListResponseModel<AlbumModel>> albumObservable = GCAlbums.Observables.list(PER_PAGE);
        albumObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<ListResponseModel<AlbumModel>>() {
                    @Override
                    public void onNext(ListResponseModel<AlbumModel> value) {
                        Log.d(TAG, "Album response: " + value.toString());
                        Snackbar.make(root, R.string.snackbar_response_success, Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "An error occurred: " + e.getMessage());
                        Snackbar.make(root, R.string.snackbar_response_error, Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getAlbumsUsingCall() {
        Call<ListResponseModel<AlbumModel>> albumCall = GCAlbums.Calls.list(PER_PAGE);
        albumCall.enqueue(new Callback<ListResponseModel<AlbumModel>>() {
            @Override
            public void onResponse(Call<ListResponseModel<AlbumModel>> call, Response<ListResponseModel<AlbumModel>> response) {
                Log.d(TAG, "Album response: " + response.toString());
                Snackbar.make(root, R.string.snackbar_response_success, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ListResponseModel<AlbumModel>> call, Throwable t) {
                Log.e(TAG, "An error occurred: " + t.getMessage());
                Snackbar.make(root, R.string.snackbar_response_error, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
