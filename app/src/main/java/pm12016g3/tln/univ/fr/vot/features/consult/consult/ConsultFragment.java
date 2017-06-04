package pm12016g3.tln.univ.fr.vot.features.consult.consult;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview.ConsultCardItem;
import pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview.ConsultCardViewAdapter;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.example.ParticipationActivity_;
import pm12016g3.tln.univ.fr.vot.features.consult.result.ResultActivity_;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.views.ClickListener;
import pm12016g3.tln.univ.fr.vot.utilities.views.recycler.RecyclerTouchListener;

import static android.view.View.VISIBLE;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.
 * File ConsultFragment.java.
 * Created by Ysee on 15/05/2017 - 10:12.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_consult_fragment)
public class ConsultFragment extends Fragment implements ClickListener {
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

    /**
     * Progress view
     */
    LoaderDialog progressView;

    /**
     * Social choices
     */
    List<SocialChoice> socialChoiceList = new ArrayList<>();

    /**
     * Consult Card Items
     */
    List<ConsultCardItem> consultCardItemList = new ArrayList<>();

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
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.KEMENY_YOUNG,true,true));
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.MAJORITY_JUGMENT,true,false));
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.SIMPLE_TRANSFARABLE_VOTE,true,false));
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.KEMENY_YOUNG,true,false));

        for(SocialChoice sc : socialChoiceList){
            consultCardItemList
                    .add(new ConsultCardItem(sc.getType(),sc.getTitle(),sc.isClosed()));
        }

        adapter.addAll(consultCardItemList);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView,
                this));

        progressView.dismiss();
    }

    @Override
    public void onClick(View view, int position) {

       int visibility = view.findViewById(R.id.is_closed_tv).getVisibility();
        if(visibility==VISIBLE){
            startActivity(new Intent(getActivity().getApplicationContext(),ResultActivity_.class));
        }else {
            startActivity(new Intent(getActivity().getApplicationContext(),ParticipationActivity_.class));
        }
    }

    @Override
    public void onLongClick(View view, int position) {
        Log.d(TAG, "onLongClick...");
    }
}
