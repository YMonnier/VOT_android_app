package pm12016g3.tln.univ.fr.vot.features.consult.consult;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview.ConsultCardViewAdapter;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withOrder.SimpleVoteWithOrderParticipationActivity_;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.stv.STVParticipationActivity_;
import pm12016g3.tln.univ.fr.vot.features.consult.result.ResultActivity_;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.models.shared.SCKemenyYoung;
import pm12016g3.tln.univ.fr.vot.models.shared.SCMajorityJudgment;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSimpleTransfarableVote;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTServiceAPI;
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

    @RestService
    VOTServiceAPI serviceAPI;

    /**
     * Progress view
     */
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
    @Background
    void loadData() {

        try {
            showProgress();
            Log.d(TAG, String.valueOf(Settings.currentUser));
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<List<JsonObject>>> response = serviceAPI.getSocialChoices();
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                List<JsonObject> socialChoices = response.getBody().getData();
                GsonDeserializer gde = new GsonDeserializer();
                if (socialChoices != null) {
                    for (JsonObject socialChoiceJson : socialChoices) {

                        String stype = socialChoiceJson.get(JsonKeys.TYPE).getAsString();
                        SocialChoice.Type type = SocialChoice.Type.valueOf(stype);

                        if (type == SocialChoice.Type.SM) {
                            SocialChoice<SCSMajorityBallot> sc = gde.deserialize(socialChoiceJson, SCSMajorityBallot.class);
                            adapter.add(sc);
                            Log.d(TAG, sc.toString());
                        } else if (type == SocialChoice.Type.KY) {
                            SocialChoice<SCKemenyYoung> sc = gde.deserialize(socialChoiceJson, SCKemenyYoung.class);
                            adapter.add(sc);
                            Log.d(TAG, sc.toString());
                        } else if (type == SocialChoice.Type.JM) {
                            SocialChoice<SCMajorityJudgment> sc = gde.deserialize(socialChoiceJson, SCMajorityJudgment.class);
                            adapter.add(sc);
                            Log.d(TAG, sc.toString());
                        } else if (type == SocialChoice.Type.STV) {
                            SocialChoice<SCSimpleTransfarableVote> sc = gde.deserialize(socialChoiceJson, SCSimpleTransfarableVote.class);
                            adapter.add(sc);
                            Log.d(TAG, sc.toString());
                        }
                    }
                    setAdapter();
                    dismissProgress();
                    //recyclerView.setAdapter(adapter);
                    /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                            recyclerView,
                            this));*/
                }
            } else {
                dismissProgress();
            }
        } catch (RestClientException e) {
            Log.d(TAG, e.getLocalizedMessage());
            dismissProgress();
        }
/*
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.KY,true,true));
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.JM,true,false));
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.SM,true,false));
        socialChoiceList
                .add(new SocialChoice("super title","description",SocialChoice.Type.STV,true,false));

        List<ConsultCardItem> consultCardItemList = new ArrayList<>();
        for(SocialChoice sc : socialChoiceList){
            consultCardItemList
                    .add(new ConsultCardItem(sc.getType(),sc.getTitle(),sc.isClosed()));
        }

        adapter.addAll(consultCardItemList);
*/
        //progressView.dismiss();
    }

    @Override
    public void onClick(View view, int position) {

        SocialChoice.Type type = adapter.getItems().get(position).getType();

        int visibility = view.findViewById(R.id.is_closed_tv).getVisibility();

        if (visibility == VISIBLE) {
            startActivity(new Intent(getActivity().getApplicationContext(), ResultActivity_.class));
        } else {
            switch (type) {
                case STV:
                    startActivity(
                            new Intent(getActivity().
                                    getApplicationContext(),
                                    STVParticipationActivity_.class));
                    break;
                case JM:

                    break;
                case SM:
                    startActivity(
                            new Intent(getActivity().
                                    getApplicationContext(),
                                    SimpleVoteWithOrderParticipationActivity_.class));
                    break;
                case KY:

                    break;
            }

        }
    }

    @Override
    public void onLongClick(View view, int position) {
        Log.d(TAG, "onLongClick...");
    }

    @UiThread
    void showProgress() {
        progressView.show();
    }

    @UiThread
    void dismissProgress() {
        progressView.dismiss();
    }

    @UiThread
    void setAdapter() {
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView,
                this));
    }
}
