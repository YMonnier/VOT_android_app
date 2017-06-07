package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.fragment.AppFragment;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File RecapFragment.java.
 * Created by Ysee on 30/05/2017 - 17:03.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_recap_fragment)
@OptionsMenu(R.menu.consult_create_menu_back_arrow)
public class RecapFragment extends AppFragment {
    private static final String TAG = RecapFragment.class.getSimpleName();

    @ViewById(R.id.recap_title)
    TextView recapTitle;

    @ViewById(R.id.recap_type)
    TextView recapType;

    @ViewById(R.id.recap_description)
    TextView recapDescription;

    @ViewById(R.id.recap_confidentiality)
    TextView recapConfidentiality;

    @ViewById(R.id.recap_date)
    TextView recapDate;

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        fragmentTitle = getString(R.string.fragment_title_recap);
        parent = (CreateFragment) getParentFragment();
        recapTitle.setText(parent.getSocialChoice().getTitle());
        recapType.setText(parent.getSocialChoice().getType().toString());
        recapDescription.setText(parent.getSocialChoice().getDescription());
        String confidentiality;
        if (parent.getSocialChoice().isConfidentiality()) {
            recapConfidentiality.setText("Anonyme");
        } else {
            recapConfidentiality.setText("Non Anonyme");
        }
    }

    @OptionsItem(R.id.menu_item_back_arrow)
    void previous() {
        Log.d(TAG, "Back button");
        parent.previousStep();
    }
}
