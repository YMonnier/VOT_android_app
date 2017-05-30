package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation;

import android.app.Fragment;
import android.widget.CompoundButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends.InvitationFriendsFragment_;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File InvitationFragment.java.
 * Created by Ysee on 29/05/2017 - 10:12.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_invitation_fragment)
public class InvitationFragment extends Fragment
        implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = InvitationFragment.class.getSimpleName();
    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        parent = (CreateFragment) getParentFragment();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        setFragment(new InvitationFriendsFragment_());
        parent.nextStep();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    /**
     * Change the content view with a specific fragment.
     *
     * @param fragment fragment to replace.
     */
    public void setFragment(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, fragment)
                .commit();
    }



}
