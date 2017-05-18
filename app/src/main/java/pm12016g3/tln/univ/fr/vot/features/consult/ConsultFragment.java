package pm12016g3.tln.univ.fr.vot.features.consult;

import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.cardview.ConsultCardItem;
import pm12016g3.tln.univ.fr.vot.features.consult.cardview.ConsultCardViewAdapter;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.
 * File ConsultFragment.java.
 * Created by Ysee on 15/05/2017 - 10:12.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_consult_fragment)
public class ConsultFragment extends Fragment
        implements RecyclerView.OnItemTouchListener {
    private final static String TAG = ConsultFragment.class.getSimpleName();

    /**
     * Number of card view per 'row'.
     */
    private final static int NUMBER_OF_ITEM_PER_ROW = 3;

    /**
     * Cardview adapter to manage card item.
     */
    @Bean
    ConsultCardViewAdapter adapter;

    /**
     * Main container.
     */
    @ViewById
    RecyclerView recyclerView;

    LoaderDialog progressView;

    @AfterViews
    void init() {
        Log.d(TAG, "Init fragment...");
        progressView = new LoaderDialog(getActivity(), "");
        loadData();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    /**
     * Function allowing to get information
     * from server and put them to the adapter
     * <p>
     * This task is done into the background thread.
     */
    void loadData() {
        // TODO: Background task + API Request
        progressView.show();

        adapter
                .add(new ConsultCardItem(SocialChoice.Type.SIMPLE, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.SIMPLE_TRANSFARABLE_VOTE, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.KEMENY_YOUNG, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.KEMENY_YOUNG, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.KEMENY_YOUNG, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.KEMENY_YOUNG, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.KEMENY_YOUNG, "Super Title..."))
                .add(new ConsultCardItem(SocialChoice.Type.MAJORITY_JUGMENT, "Super Title..."));
        recyclerView.setAdapter(adapter);

        progressView.dismiss();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent");
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d(TAG, "onTouchEvent");
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.d(TAG, "onRequestDisallowInterceptTouchEvent");
    }
}
