package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.consult.consult.ConsultFragment_;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;
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

    /**
     * Title Text View
     */
    @ViewById(R.id.recap_title)
    TextView recapTitle;

    /**
     * Algorithm Type Text View
     */
    @ViewById(R.id.recap_type)
    TextView recapType;

    /**
     * Description Text View
     */
    @ViewById(R.id.recap_description)
    TextView recapDescription;

    /**
     * Confidentiality status Text View
     */
    @ViewById(R.id.recap_confidentiality)
    TextView recapConfidentiality;

    /**
     * Date Text View
     */
    @ViewById(R.id.recap_date)
    TextView recapDate;

    /**
     * Candidat List view.
     */
    @ViewById(R.id.recap_candidat)
    ListView candidatListView;

    /**
     * Participant ListView
     */
    @ViewById(R.id.recap_participant)
    ListView participantListView;

    @ViewById(R.id.send_bt)
    Button sendButton;

    /**
     * String Array Adapter for candidat
     */
    ArrayAdapter<String> candidatAdapter;

    /**
     * String Array Adapter for participant
     */
    ArrayAdapter<String> participantAdapter;

    /**
     * VOT Social Choice API
     */
    @RestService
    VOTSocialChoiceAPI serviceAPI;

    /**
     * VOT Friend Request API.
     */
    @RestService
    VOTFriendsAPI friendsAPI;

    /**
     * Current Social Choice
     */
    SocialChoice socialChoice;

    /**
     * Progress Dialog
     */
    private LoaderDialog progressView;

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
        progressView = new LoaderDialog(getActivity(), getString(R.string.in_progress));
        socialChoice = parent.getSocialChoice();
        //setAdapters(parent.getSocialChoice());
        recapTitle.setText(socialChoice.getTitle());
        recapDescription.setText("Description : " + socialChoice.getDescription());
        long time = Long.valueOf(socialChoice.getEndDate()).longValue();
        Date dateend = new Date(time);
        Timestamp endtime = new Timestamp(dateend.getTime());
        recapDate.setText("Clos le : " + endtime.toString());

        switch (socialChoice.getType()) {
            case JM:
                recapType.setText(getString(R.string.algo_jm));
                break;
            case STV:
                recapType.setText(getString(R.string.algo_stv));
                break;
            case KY:
                recapType.setText(getString(R.string.algo_ky));
                break;
            case SM:
                recapType.setText(getString(R.string.algo_ms));
        }


        if (parent.getSocialChoice().isConfidentiality()) {
            recapConfidentiality.setText(R.string.algo_anonymous);
        } else {
            recapConfidentiality.setText(R.string.algo_not_anonymous);
        }

        List<String> candidats = new ArrayList<>();
        for (Object candidat : socialChoice.getCandidats()) {
            candidats.add(((Candidat) candidat).getName());
        }
        candidatAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                candidats);
        candidatListView.setAdapter(candidatAdapter);


        List<String> participants = new ArrayList<>();
        for (Object user : socialChoice.getParticipants()) {
            participants.add(((User) user).getPseudo());
        }
        participantAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                participants);
        participantListView.setAdapter(participantAdapter);


    }

    @OptionsItem(R.id.menu_item_back_arrow)
    void previous() {
        Log.d(TAG, "Back button");
        parent.previousStep();
    }

    @Click(R.id.send_bt)
    void sendAction() {
        try {
            showProgress();
            updateLockUi(true);
            sendSocialChoice();
        } catch (RestClientException e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    // TODO: Comments

    /**
     * Send the current Social Choice created by the user to the Server.
     * <p>
     * This task is executed in background.
     */
    @Background
    void sendSocialChoice() {
        serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
        ResponseEntity<Response<JsonObject>> response = serviceAPI.createSociaChoice(parent.getSocialChoice());
        Log.d(TAG, response.toString());
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            dismissProgress();
            updateLockUi(false);
            Snack.showFailureMessage(getView(),
                    getString(R.string.snack_error_http_sending),
                    Snackbar.LENGTH_LONG);
        } else {
            dismissProgress();
            goToConsultView();
        }
    }


    /**
     * Go the first view (Consult Fragment)
     * <p>
     * This task is done on the UiThread.
     */
    @UiThread
    void goToConsultView() {
        MainActivity mainActivity = (MainActivity) parent.getActivity();
        Fragment c = new ConsultFragment_();
        mainActivity.setFragment(c, getString(R.string.fragment_title_consult));
    }

    /**
     * Show the progress view.
     * This task is done on UI Thread.
     */
    @UiThread
    void showProgress() {
        progressView.show();
    }

    /**
     * Dismiss the progress view.
     * This task is done on UI Thread.
     */
    @UiThread
    void dismissProgress() {
        progressView.dismiss();
    }

    @UiThread
    void updateLockUi(final boolean status) {
        sendButton.setEnabled(!status);
    }
}
