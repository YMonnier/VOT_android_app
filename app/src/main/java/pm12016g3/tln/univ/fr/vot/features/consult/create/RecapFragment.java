package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File RecapFragment.java.
 * Created by Ysee on 30/05/2017 - 17:03.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_recap_fragment)
public class RecapFragment extends Fragment {

    @ViewById(R.id.recap_view)
    TextView recapView;

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        parent = (CreateFragment) getParentFragment();
        recapView.setText(parent.getSocialChoice().toString());
    }
}
