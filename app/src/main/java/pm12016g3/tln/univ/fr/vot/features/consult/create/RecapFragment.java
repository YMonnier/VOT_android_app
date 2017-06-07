package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.google.android.gms.games.multiplayer.Participant;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;

import com.google.gson.JsonObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import pm12016g3.tln.univ.fr.vot.R;

import pm12016g3.tln.univ.fr.vot.features.consult.create.recap.RecapListViewAdapter;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;

import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;

import pm12016g3.tln.univ.fr.vot.utilities.views.fragment.AppFragment;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

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


    @ViewById(R.id.recap_candidat)
    ListView candidatListView;

    @ViewById(R.id.recap_participant)
    ListView participantListView;

    @Bean
    RecapListViewAdapter candidatAdapter;

    @Bean
    RecapListViewAdapter participantAdapter;

    @RestService
    VOTSocialChoiceAPI serviceAPI;

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
        setAdapters(parent.getSocialChoice());
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

    @UiThread
    void setAdapters(SocialChoice socialChoice) {
        for ( Object candidat : socialChoice.getCandidats()) {
            candidatAdapter.add(new BasicItem(((Candidat)candidat).getName()));
            candidatListView.setAdapter(candidatAdapter);
        }
        for (Object participant : socialChoice.getParticipants()) {
            participantAdapter.add(new BasicItem(((User)participant).getPseudo()));
            participantListView.setAdapter(participantAdapter);
        }
    }

    @OptionsItem(R.id.menu_item_back_arrow)
    void previous() {
        Log.d(TAG, "Back button");
        parent.previousStep();
    }

    @Click(R.id.send_bt)
    void sendAction() {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<JsonObject>> response = serviceAPI.createSociaChoice(parent.getSocialChoice());
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                Snack.showFailureMessage(getView(),
                        getString(R.string.snack_error_http_400_500),
                        Snackbar.LENGTH_LONG);
            }
        } catch (RestClientException e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }
}
