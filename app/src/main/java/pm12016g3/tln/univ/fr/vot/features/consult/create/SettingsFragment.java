package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.SimpleVoteFragment_;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File SettingsFragment.java.
 * Created by Ysee on 19/05/2017 - 17:50.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_settings_fragment)
@OptionsMenu(R.menu.consult_create_menu_one_arrow)
public class SettingsFragment extends Fragment
        implements Validable {
    private static final String TAG = SettingsFragment.class.getSimpleName();

    /**
     * Default spinner value
     * That attribute should not be selected.
     */
    private final String DEFAULT_ALGORITHM = "Type";

    /**
     * Confidentiality boolean.
     * The user can swift on/off
     * the confidentiality parameter.
     */
    @ViewById(R.id.confidentiality)
    Switch confidentiality;

    /**
     * List of algorithms
     */
    @ViewById(R.id.algorithms)
    Spinner algorithms;

    /**
     * Social Choice Title.
     */
    @ViewById(R.id.input_title)
    EditText title;

    /**
     * Optional Social Choice description
     */
    @ViewById(R.id.input_desc)
    EditText description;


    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        parent = (CreateFragment) getParentFragment();
    }

    @OptionsItem(R.id.menu_item_next_arrow)
    void next() {
        Log.d(TAG, "Next button");
        if (validate()) {
            ViewUtils.closeKeyboard(getActivity(),
                    getActivity().getCurrentFocus());
            parent.nextStep();
            parent.setFragment(new SimpleVoteFragment_(), "Vote Simple");
        }
    }

    /**
     * Check all input data when user
     * press on next button edit text.
     *
     * @param textView action source
     * @param actionId type of action(IME_ID)
     * @param keyEvent event
     */
    @EditorAction({R.id.input_title})
    void onNextActionsEditText(TextView textView,
                               int actionId,
                               KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            validate();
        }
    }

    @Override
    public boolean validate() {
        updateResetErrorUi();

        boolean cancel = false;
        View focusView = null;

        Object selectedItem = algorithms.getSelectedItem();
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();

        assert selectedItem != null;
        if (TextUtils.isEmpty(title)) {
            updateErrorUi(this.title, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = this.title;
            cancel = true;
        } else if (TextUtils.isEmpty(description)) {
            updateErrorUi(this.description, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = this.description;
            cancel = true;
        } else if (selectedItem != null) {
            String algorithm = selectedItem.toString();
            Log.d(TAG, algorithm);
            if (algorithm.equals(DEFAULT_ALGORITHM)) {
                cancel = true;
                if (focusView == null)
                    focusView = algorithms;
            }
        }

        Log.d(TAG, String.valueOf(cancel));
        if (cancel) {
            // There was an error; don't attempt, focus on the first
            // form field with an error.
            assert focusView != null;
            if (focusView != null)
                focusView.requestFocus();
        }


        return !cancel;
    }

    /**
     * Reset all error input views.
     */
    @UiThread
    void updateResetErrorUi() {
        // Reset errors.
        title.setError(null);
        description.setError(null);
        confidentiality.setError(null);
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

    @UiThread
    void updateErrorUi(final Switch view, final String error) {
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
        title.setEnabled(!status);
        description.setEnabled(!status);
        algorithms.setEnabled(!status);
    }

    /**
     * Handler when user
     * clicks on input description to hide
     * keyboard if necessary.
     */
    @Click(R.id.input_desc)
    void clickOnInputDescription() {
        Log.d(TAG, "clickOnInputDescription");
        ViewUtils.closeKeyboard(getActivity(),
                getActivity().getCurrentFocus());
    }

    @Click(R.id.algorithms_help)
    void algorithmsHelper() {
        Log.d(TAG, "OnClick - Algorithm Helper....");
    }

    @Click(R.id.confidentiality_help)
    void confidentialityHelper() {
        Log.d(TAG, "OnClick - Confidentiality Helper....");
    }
}
