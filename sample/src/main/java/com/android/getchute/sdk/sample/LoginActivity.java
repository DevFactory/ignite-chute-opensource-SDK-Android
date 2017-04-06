package com.android.getchute.sdk.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.getchute.sdk.chutesdkandroid.api.authentication.TokenAuthenticationProvider;
import com.android.getchute.sdk.chutesdkandroid.api.service.auth.GCAuthentication;
import com.android.getchute.sdk.chutesdkandroid.model.LoginResponseModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.editTextUsername)
    EditText editTextUsername;
    @Bind(R.id.editTextPassword)
    EditText editTextPassword;
    @Bind(R.id.buttonLogin)
    Button buttonLogin;
    @Bind(R.id.root)
    LinearLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TokenAuthenticationProvider.getInstance().isTokenValid()) {
            startMainActivity();
            return;
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void onLoginButtonClick() {

        String email = editTextUsername.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextUsername.setError(getString(R.string.edittext_username_error));
        } else if (TextUtils.isEmpty(editTextPassword.getText())) {
            editTextPassword.setError(getString(R.string.edittext_password_error));
        } else {
            login();
        }
    }

    private void login() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        Observable<LoginResponseModel> loginObservable = GCAuthentication.Observables.login(username, password, App.CLIENT_ID, App.CLIENT_SECRET);
        loginObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginResponseModel>() {
                    @Override
                    public void onNext(LoginResponseModel value) {
                        if (!TextUtils.isEmpty(value.getAccessToken())) {
                            String token = value.getAccessToken();
                            TokenAuthenticationProvider tokenProvider = TokenAuthenticationProvider
                                    .getInstance();
                            tokenProvider.setToken(token);
                            Timber.d("token: " + token);
                            startMainActivity();
                        } else {
                            Snackbar.make(root, R.string.snackbar_empty_token, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("Error logging in: %s , %s", e.getCause(),
                                e.getMessage());
                        Snackbar.make(root, R.string.snackbar_response_error, Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
