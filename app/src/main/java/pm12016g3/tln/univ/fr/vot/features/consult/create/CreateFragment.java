package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.SimpleVoteFragment;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.views.custom.LifelineView;
import pm12016g3.tln.univ.fr.vot.utilities.views.fragment.AppFragment;

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
    private static final String TAG = CreateFragment.class.getSimpleName();
    /**
     * Current creating social choice.
     */
    @Getter
    private SocialChoice socialChoice = new SocialChoice();

    /**
     * Stack of Fragment.
     */
    @Getter
    private List<AppFragment> fragments;

    /**
     * Lifeline index.
     */
    private int lifelineViewIndex = 1;

    @ViewById(R.id.lifelineView)
    LifelineView lifelineView;

    @AfterViews
    void init() {
        fragments = new ArrayList<>();
        lifelineView.setSelected(lifelineViewIndex);
        initDefaultFragment();
    }

    /**
     * Initialize the defaut fragment.
     */
    private void initDefaultFragment() {
        SettingsFragment sf = new SettingsFragment_();
        setFragment(sf);
    }

    /**
     * Change the content view with a specific fragment.
     * @param fragment fragment to replace.
     */
    private void setFragment(AppFragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, fragment)
                .commit();
        getActivity().setTitle(fragment.getFragmentTitle());
    }

    /**
     * Show the next fragment and add
     * the source fragment to the fragment manager.
     *
     * @param next next fragment to display.
     * @param source source fragment to add.
     */
    @UiThread
    public void nextStep(AppFragment source, AppFragment next) {
        Log.d(TAG, "Next Step with source");
        lifelineViewIndex += 1;
        lifelineView.setSelected(lifelineViewIndex);
        if (!fragments.contains(source))
            fragments.add(source);
        setFragment(next);
        Log.d(TAG, toString());
    }

    /**
     * Show the existing next fragment.
     */
    @UiThread
    public void nextStep() {
        Log.d(TAG, "Next Step with existing view");
        lifelineViewIndex += 1;
        lifelineView.setSelected(lifelineViewIndex);
        setFragment(fragments.get(lifelineViewIndex - 1));
        Log.d(TAG, toString());
    }

    /**
     * Decrement the lifeline view + Show the previous fragment.
     */
    @UiThread
    public void previousStep() {
        Log.d(TAG, "Previous Step");
        lifelineViewIndex -= 1;
        lifelineView.setSelected(lifelineViewIndex);
        setFragment(fragments.get(lifelineViewIndex - 1));
        Log.d(TAG, toString());
    }

    /**
     * Return the type of the algorithm view.
     * @return SocialChoice.Type
     */
    public SocialChoice.Type algorithmTypeFragment() {
        Log.d(TAG, "algorithmTypeFragment... ");
        SocialChoice.Type res = null;
        if (fragments.size() > 1) {
            AppFragment tmp = fragments.get(1);
            if (tmp instanceof SimpleVoteFragment)
                res = SocialChoice.Type.SM;
        }
        return res;
    }

    @Override
    public String toString() {
        return "CreateFragment{" +
                "socialChoice=" + socialChoice +
                ", fragments=" + fragments +
                ", lifelineViewIndex=" + lifelineViewIndex +
                '}';
    }
}
