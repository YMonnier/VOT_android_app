package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import lombok.Getter;
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
    @Getter
    private SocialChoice socialChoice = new SocialChoice();

    private int lifelineViewIndex = 1;

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
    public void setFragment(Fragment fragment, String title) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, fragment)
                .commit();
        getActivity().setTitle(title);
    }

    @UiThread
    public void nextStep() {
        lifelineViewIndex += 1;
        lifelineView.setSelected(lifelineViewIndex);
    }

    @UiThread
    public void previousStep() {
        lifelineViewIndex -= 1;
        lifelineView.setSelected(lifelineViewIndex);
    }
}
