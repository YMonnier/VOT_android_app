package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms;

import android.app.Fragment;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.
 * File SimpleVoteFragment.java.
 * Created by Ysee on 22/05/2017 - 15:10.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_algo_simple_vote_fragment)
@OptionsMenu(R.menu.consult_create_menu_two_arrows)
public class SimpleVoteFragment extends Fragment {
    private static final String TAG = SimpleVoteFragment.class.getSimpleName();

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        Log.d(TAG, "Init");
        parent = (CreateFragment) getParentFragment();
        Log.d(TAG, String.valueOf(parent));
    }
}
