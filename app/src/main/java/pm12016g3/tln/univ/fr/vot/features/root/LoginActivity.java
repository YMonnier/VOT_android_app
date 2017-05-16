package pm12016g3.tln.univ.fr.vot.features.root;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.shared.main.MainActivity_;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.validator.EmailValidator;
import pm12016g3.tln.univ.fr.vot.utilities.validator.PasswordValidator;

/**
 * Project VOT.
 * Package pm12016g3.tln.univ.fr.vot.features.root.
 * File LoginActivity.java.
 * Created by Ysee on 11/05/2017 - 17:52.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EActivity(R.layout.root_login_activity)
public class LoginActivity extends AppCompatActivity {

    private final static String TAG = LoginActivity.class.getSimpleName();

    /**
     * Input email used to authenticate the user.
     */
    @ViewById(R.id.input_email)
    EditText emailView;

    /**
     * Input password used to authenticate the user.
     */
    @ViewById(R.id.input_password)
    EditText passwordView;

    /**
     * Button action to login to the API.
     */
    @ViewById(R.id.btn_confirmation)
    Button loginConfirmation;

    /**
     * Progress Dialog
     */
    private LoaderDialog progressView;

    @AfterViews
    public void init() {
        Log.i(TAG, "Initialize Registration Activity...");
        progressView = new LoaderDialog(this, getString(R.string.login_progress_label));
    }


    /**
     * Check all input data when user
     * press on next button edit text.
     *
     * @param textView action source
     * @param actionId type of action(IME_ID)
     * @param keyEvent event
     */
    @EditorAction({R.id.input_email, R.id.input_password})
    void onNextActionsEditText(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == R.id.login || actionId == EditorInfo.IME_ACTION_NEXT) {
            attemptLogin();
        }
    }

    /**
     * Sign in button action.
     */
    @Click(R.id.btn_confirmation)
    void onClickOnSigninButton() {
        goToHomeView();
        //attemptLogin();
    }

    /**
     * Sign up view action
     */
    @Click(R.id.link_registration)
    void onClickOnSignupLink() {
        startActivity(new Intent(this, RegistrationActivity_.class));
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        emailView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            updateErrorUi(passwordView, getString(R.string.login_error_field_required));
            focusView = passwordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            updateErrorUi(passwordView, getString(R.string.login_error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            updateErrorUi(emailView, getString(R.string.login_error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            updateErrorUi(emailView, getString(R.string.login_error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            assert focusView != null;
            if (focusView != null)
                focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            progressView.show();
            userLoginTask(email, password);
        }
    }

    /**
     * Update the current view by settings error
     * message to the input view.
     *
     * @param view  input view
     * @param error error message
     */
    @UiThread
    void updateErrorUi(final EditText view, final String error) {
        view.setError(error);
    }

    /**
     * Update clickable button depending on a status
     *
     * @param status true if we want to
     *               disable all clickable buttons, otherwise, false
     */
    @UiThread
    void updateLockUi(boolean status) {
        passwordView.setEnabled(!status);
        emailView.setEnabled(!status);
        loginConfirmation.setEnabled(!status);
    }

    /**
     * Check if the email is valid.
     * That is to say, check if the pattern is valid: mail@mail.com
     *
     * @param email string email
     * @return true if valid, otherwise, false
     */
    private boolean isEmailValid(final String email) {
        return EmailValidator.validate(email);
    }

    /**
     * Check the password length
     *
     * @param password password for validation
     * @return true if password length >= 8, otherwise, false
     */
    private boolean isPasswordValid(final String password) {
        return PasswordValidator.validate(password);
    }

    /**
     * Function allowing to navigate
     * to the next view (`ConsultActivity`)
     */
    private void goToHomeView() {
        startActivity(new Intent(this, MainActivity_.class));
    }

    /**
     * Background task which allows
     * to send credential data to the server.
     * If the connexion is succeed, we go to the sessions list.
     * Otherwise, the user put wrong data and should try again...
     *
     * @param email    user email
     * @param password password email
     */
    @Background
    void userLoginTask(final String email, final String password) {
        updateLockUi(true);
        // TODO: Add HTTP Request to login...
        progressView.show();

        try {
            Thread.sleep(1000);
            // Do some stuff

        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        progressView.dismiss();
    }
}
