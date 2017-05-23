package pm12016g3.tln.univ.fr.vot.features.root;

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
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.validator.EmailValidator;
import pm12016g3.tln.univ.fr.vot.utilities.validator.PasswordValidator;

/**
 * Project VOT.
 * Package pm12016g3.tln.univ.fr.vot.features.root.
 * File RegistrationActivity.java.
 * Created by Ysee on 12/05/2017 - 10:19.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
@EActivity(R.layout.root_registration_activity)
public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = RegistrationActivity.class.getSimpleName();
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
     * Input confirmation password used to confirm the previous input.
     */
    @ViewById(R.id.input_confirmation_password)
    EditText passwordConfirmationView;

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
        progressView = new LoaderDialog(this, getString(R.string.registration_progress_label));
    }

    /**
     * Check all input data when user
     * press on next button edit text.
     *
     * @param textView action source
     * @param actionId type of action(IME_ID)
     * @param keyEvent event
     */
    @EditorAction({R.id.input_email,
            R.id.input_password,
            R.id.input_confirmation_password})
    void onNextActionsEditText(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == R.id.login || actionId == EditorInfo.IME_ACTION_NEXT) {
            attemptSignup();
        }
    }

    /**
     * Sign up button action.
     */
    @Click(R.id.btn_confirmation)
    void onClickOnRegisterButton() {
        attemptSignup();
    }


    /**
     * Attempts to register a account.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual signup attempt is made.
     */
    private void attemptSignup() {
        updateResetErrorUi();

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String confirmPassword = passwordConfirmationView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            updateErrorUi(emailView, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            updateErrorUi(emailView, getString(R.string.error_invalid_email));
            if (focusView == null)
                focusView = emailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            updateErrorUi(passwordView, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = passwordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            updateErrorUi(passwordView, getString(R.string.error_invalid_password));
            if (focusView == null)
                focusView = passwordView;
            cancel = true;
        }else if(TextUtils.isEmpty(confirmPassword)) {
            updateErrorUi(passwordConfirmationView, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = passwordConfirmationView;
            cancel = true;
        }else if (!isPasswordValid(password, confirmPassword)) {
            updateErrorUi(passwordConfirmationView, getString(R.string.error_not_matching_password));
            if (focusView == null)
                focusView = passwordConfirmationView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt, focus on the first
            // form field with an error.
            assert focusView != null;
            if (focusView != null)
                focusView.requestFocus();

        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            progressView.show();
            signupTask(email, password);
        }
    }

    /**
     * Reset all error input views.
     */
    @UiThread
    void updateResetErrorUi() {
        // Reset errors.
        emailView.setError(null);
        passwordView.setError(null);
    }

    /**
     * Update the current view by settings error
     * message to the input view.
     *
     * @param view  EditText view
     * @param error error message
     */
    @UiThread
    void updateErrorUi(EditText view, String error) {
        view.setError(error);
    }

    /**
     * Update the current view by settings error
     * message to the text view.
     *
     * @param view  TextView view
     * @param error error message
     */
    @UiThread
    void updateErrorUi(final TextView view, final String error) {
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
        emailView.setEnabled(!status);
        passwordView.setEnabled(!status);
        passwordConfirmationView.setEnabled(!status);
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
     * Check if password and confirmation password match.
     *
     * @param password        password for validation
     * @param confirmPassword confirmation password for validation
     * @return true if password equals confirmPassword >= 8, otherwise, false
     */
    private boolean isPasswordValid(final String password, final String confirmPassword) {
        return PasswordValidator.validate(password) && password.equals(confirmPassword);
    }

    /**
     * Background task which allows
     * to send credential data to the server.
     * If the connexion is succeed, we go to the cards view vote.
     * Otherwise, the user put wrong data and should try again...
     *
     * @param email    user email
     * @param password password email
     */
    /**
     * Background task which allows
     * to send registration data to the server.

     * @param email user email
     * @param password user password
     */
    @Background
    void signupTask(final String email,
                    final String password) {
        updateLockUi(true);

        // TODO: Http Request to signup.
    }
}
