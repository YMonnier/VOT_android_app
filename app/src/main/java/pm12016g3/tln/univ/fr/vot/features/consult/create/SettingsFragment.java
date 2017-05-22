package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.SimpleVoteFragment_;

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
     * Confidentiality boolean.
     * The user can swift on/off the confidentiality.
     */
    @ViewById(R.id.confidentiality)
    Switch confidentiality;

    /**
     * List of algorithms
     */
    @ViewById(R.id.algorithms)
    Spinner algorithms;

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
            parent.nextStep();
            parent.setFragment(new SimpleVoteFragment_(), "Vote Simple");
        }
    }

    @Override
    public boolean validate() {
        return false;
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
        //passwordView.setEnabled(!status);
        //emailView.setEnabled(!status);
        //loginConfirmation.setEnabled(!status);
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
