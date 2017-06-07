package pm12016g3.tln.univ.fr.vot.features.root;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.iid.FirebaseInstanceId;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.NetworkUtils;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTAuthAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;

/**
 * Project VOT.
 * Package pm12016g3.tln.univ.fr.vot.features.root.
 * File LoginActivity.java.
 * Created by Ysee on 11/05/2017 - 17:52.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EActivity
public class LoginActivity extends AppCompatActivity
        implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;
    //private static final String TMP_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0ODE4OTA4NDIsInN1YiI6NH0.H3WUro_qfBBU1BLvB-nQdSahSiEZ454MoKeYhvwYgr0";

    /**
     * Progress Dialog
     */
    private LoaderDialog progressView;

    /**
     * The CallbackManager manages the callbacks into the FacebookSdk from an Activity's or
     * Fragment's onActivityResult() method.
     */
    private CallbackManager callbackManager;

    /**
     * Google Api Client
     */
    public static GoogleApiClient googleApiClient;

    /**
     * Rest service to get
     * information from server.
     */
    @RestService
    VOTAuthAPI apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Initialize Registration Activity...");
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.root_login_activity);

        progressView = new LoaderDialog(this, getString(R.string.login_progress_label));

        setupFacebookOAuth();
        setupGoogleOAuth();

        checkAuthentication();

        initRealmDatabase();

        String deviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Device token: " + deviceToken);
    }

    /**
     * Init the global Realm configuration.
     */
    private void initRealmDatabase() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    /**
     * Setup the google OAuth SDK.
     */
    private void setupGoogleOAuth() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();

        googleApiClient.connect();
        Settings.googleApiClient = googleApiClient;

        SignInButton signInButton = (SignInButton) findViewById(R.id.login_google_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
    }

    /**
     * Setup the facebook OAuth SDK.
     */
    private void setupFacebookOAuth() {
        // OAuth2 Facebook
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();

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

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoogleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
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
        if (NetworkUtils.isNetworkConnected(getApplicationContext())) {
            googleSignIn();

        } else {
            Log.e(TAG, "No connection!....");
            Snack.showSuccessfulMessage(getWindow().getDecorView().findViewById(android.R.id.content),
                    getString(R.string.snack_error_no_internet),
                    Snackbar.LENGTH_LONG);
        }
    }

    //
    // Google API Handler
    //

    /**
     * Google Sign in
     */
    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        System.out.println("rhalalala");
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleGoogleSignInResult(final GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);

            if (acct != null) {
                Log.d(TAG, acct.toString());
                if (acct.getId() != null)
                    System.out.println(acct.getId());
                if (acct.getIdToken() != null)
                    System.out.println(acct.getIdToken());
                if (acct.getDisplayName() != null)
                    Log.d(TAG, acct.getDisplayName());
                if (acct.getEmail() != null)
                    Log.d(TAG, acct.getEmail());
                if (acct.getGivenName() != null)
                    Log.d(TAG, acct.getGivenName());

                Log.d(TAG, String.valueOf(acct.getGrantedScopes()));
                if (acct.getAccount() != null)
                    Log.d(TAG, acct.getAccount().toString());

                loginAction(acct);
            }
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, connectionResult.getErrorMessage());
    }


    /**
     * Action when user tap on login button.
     * <p>
     * This action sends a HTTP Request to
     * the VOT API to log in to the system.
     * <p>
     * If the request returns a response with 404 status
     * that means the user does not exist into the system.
     * <p>
     * The user should inform his nickname to log in.
     *
     * @param googleSignInAccount Google Api Client
     */
    @Background
    void loginAction(final GoogleSignInAccount googleSignInAccount) {
        final String CLIENT_ID = getString(R.string.server_client_id);
        String scope = "audience:server:client_id:" + CLIENT_ID;
        String accessToken = null;

        try {
            if (googleSignInAccount.getAccount() != null) {
                accessToken = GoogleAuthUtil.getToken(getApplicationContext(), googleSignInAccount.getAccount().name, scope);

                final String email = googleSignInAccount.getEmail();
                final String token = FirebaseInstanceId.getInstance().getToken();
                final User user = new User.Builder()
                        .setEmail(email)
                        .setAccessToken(accessToken)
                        .setDeviceToken(token)
                        .build();

                try {

                    Log.i(TAG, "Login User...");
                    Log.i(TAG, GsonSingleton.getInstance().toJson(user));
                    System.out.println("token device 2: "+token);

                    ResponseEntity<Response<User>> response = apiService.login(user);
                    Log.i(TAG, "resposne "+response.toString());

                    if (response.getStatusCode().is2xxSuccessful()) {
                        Settings.currentUser = response.getBody().getData();
                        Settings.currentUser.setAccessToken(accessToken);
                        goToHomeView();
                    } else if (response.getStatusCode().is4xxClientError()) {
                        showNicknameAlert(googleSignInAccount, user);
                    }
                } catch (RestClientException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    showNicknameAlert(googleSignInAccount, user);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "IO Exception: " + e.getMessage());
        } catch (UserRecoverableAuthException e) {
            startActivityForResult(e.getIntent(), 987);
        } catch (GoogleAuthException e) {
            Log.e(TAG, "GoogleAuthException: " + e.getMessage());
        }


        System.out.println(" token : " + accessToken);
    }

    /**
     * Registration to the VOT system.
     *
     * @param user user information
     */
    @Background
    void registration(User user) {
        try {
            Log.d(TAG, user.toString());
            Log.d(TAG, GsonSingleton.getInstance().toJson(user));

            ResponseEntity<Response<User>> response = apiService.registration(user);
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                Settings.currentUser = user;
                goToHomeView();
            }
        } catch (RestClientException e) {
            Log.e(TAG, String.valueOf(e.getStackTrace()));
            Snack.showSuccessfulMessage(getWindow().getDecorView().findViewById(android.R.id.content),
                    getString(R.string.snack_error_registration_failed),
                    Snackbar.LENGTH_LONG);
        }
    }

    /**
     * Show the nickname dialog.
     *
     * @param googleSignInAccount google api.
     */
    @UiThread
    void showNicknameAlert(final GoogleSignInAccount googleSignInAccount, final User user) {
        Log.d(TAG, "Show Nickname Dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Authentification");
        builder.setMessage(R.string.login_nickname);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton(R.string.login_ok, (dialog, which) -> {
            String nickname = input.getText().toString();
            Log.d(TAG, nickname);

            user.setPseudo(nickname);

            Uri picture = googleSignInAccount.getPhotoUrl();
            if (picture != null) {
                user.setPicture(picture.getPath());
            }

            registration(user);
        });
        builder.setNegativeButton(R.string.login_cancel, (dialog, which) -> {
            Log.d(TAG, "Closing dialog...");
            dialog.cancel();
        });

        builder.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
