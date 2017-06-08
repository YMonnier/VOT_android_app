package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation;

import android.app.Fragment;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;
import pm12016g3.tln.univ.fr.vot.features.consult.create.RecapFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends.InvitationFriendsFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.location.LocationFriendsFragment_;
import pm12016g3.tln.univ.fr.vot.utilities.views.fragment.AppFragment;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File InvitationFragment.java.
 * Created by Ysee on 29/05/2017 - 10:12.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_invitation_fragment)
@OptionsMenu(R.menu.consult_create_menu_two_arrows)
public class InvitationFragment extends AppFragment
        implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = InvitationFragment.class.getSimpleName();

    /**
     * Switch to choose friend invitation
     * or location invitation.
     */
    @ViewById(R.id.around_me)
    Switch around_me;

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        fragmentTitle = getString(R.string.fragment_title_invitation);
        parent = (CreateFragment) getParentFragment();
        setDefaultFragment();
        around_me.setOnCheckedChangeListener(this);
    }

    @OptionsItem(R.id.menu_item_next_arrow)
    void next() {
        Log.d(TAG, "Next button");
        parent.nextStep(this, new RecapFragment_());
    }

    @OptionsItem(R.id.menu_item_back_arrow)
    void previous() {
        Log.d(TAG, "Back button");
        parent.previousStep();
    }

    private void setDefaultFragment() {
        setFragment(new InvitationFriendsFragment_());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            setFragment(new LocationFriendsFragment_());
        } else {
            setDefaultFragment();
        }
    }

    /**
     * Change the content view with a specific fragment.
     * @param fragment fragment to replace.
     */
    @UiThread
    void setFragment(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, fragment)
                .commit();
    }

    public CreateFragment getParent() {
        return parent;
    }
}
