package pm12016g3.tln.univ.fr.vot.features.root;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;

/**
 * Project VOT.
 * Package pm12016g3.tln.univ.fr.vot.features.root.
 * File LoginActivity.java.
 * Created by Ysee on 11/05/2017 - 17:52.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

//@EActivity(R.layout.root_login_activity)
public class LoginActivity extends AppCompatActivity
        implements FacebookCallback<LoginResult>, View.OnClickListener {

    private final static String TAG = LoginActivity.class.getSimpleName();

    /**
     * Button action to login to the API.
     */
    //@ViewById(R.id.login_button)
    LoginButton loginConfirmation;

    /**
     * Progress Dialog
     */
    private LoaderDialog progressView;

    /**
     * The CallbackManager manages the callbacks into the FacebookSdk from an Activity's or
     * Fragment's onActivityResult() method.
     */
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Initialize Registration Activity...");
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.root_login_activity);

        progressView = new LoaderDialog(this, getString(R.string.login_progress_label));

        // OAuth2
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();


        loginConfirmation = (LoginButton) findViewById(R.id.login_button);
        loginConfirmation.setOnClickListener(this);

        checkAuthentication();
    }

    private void checkAuthentication() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            Log.d(TAG, accessToken.getUserId());
            Log.d(TAG, String.valueOf(accessToken.getExpires()));
            Log.d(TAG, String.valueOf(accessToken.getLastRefresh()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Function allowing to navigate
     * to the next view (`ConsultFragment`)
     */
    private void goToHomeView() {
        startActivity(new Intent(this, MainActivity_.class));
    }

    @Override
    public void onClick(View view) {
        LoginManager.getInstance().registerCallback(callbackManager, this);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        String token = loginResult.getAccessToken().getToken();
        Log.d(TAG, "OnSuccess");
        Log.d(TAG, "Facebook token: " + token);
    }

    @Override
    public void onCancel() {
        Log.d(TAG, "onCancel");
    }

    @Override
    public void onError(FacebookException error) {
        Log.d(TAG, "onError: " + error.getLocalizedMessage());
    }
}
