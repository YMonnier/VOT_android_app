package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Switch;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File SettingsFragment.java.
 * Created by Ysee on 19/05/2017 - 17:50.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_settings_fragment)
@OptionsMenu(R.menu.consult_consult_menu)
public class SettingsFragment extends Fragment {
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

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        //parent = (CreateFragment) getParentFragment();
        //System.out.println(parent.socialChoice);
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
