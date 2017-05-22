package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.views.custom.LifelineView;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File CreateFragment.java.
 * Created by Ysee on 19/05/2017 - 15:09.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_fragment)
public class CreateFragment extends Fragment {

    protected SocialChoice socialChoice = new SocialChoice();
    protected int lifelineViewIndex = 1;

    @ViewById(R.id.lifelineView)
    LifelineView lifelineView;

    @AfterViews
    void init() {
        lifelineView.setSelected(lifelineViewIndex);
        initDefaultFragment();
    }

    /**
     * Initialize the defaut fragment.
     */
    private void initDefaultFragment() {
        String title = getString(R.string.fragment_title_settings);

        setFragment(new SettingsFragment_(), title);
    }

    /**
     * Change the content view with a specific fragment.
     * @param fragment fragment to replace.
     * @param title fragment title.
     */
    private void setFragment(Fragment fragment, String title) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, fragment)
                .commit();
        getActivity().setTitle(title);
    }

}