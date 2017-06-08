package pm12016g3.tln.univ.fr.vot.features.consult.participation.jm;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.google.gson.JsonObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.Vote;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.models.shared.SCMajorityJudgment;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.network.NetworkUtils;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by damienlemenager on 06/06/2017.
 */
@EActivity(R.layout.consult_participation_jm_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class JMParticipationActivity extends AppCompatActivity {

    final String TAG = JMParticipationActivity.class.getSimpleName();
    private final String TV_STRING = "Veuillez choisir une étiquette pour chaque choix.\nLes choix :";
    SocialChoice<SCMajorityJudgment> socialChoice;

    @ViewById(R.id.tv_reference)
    TextView tv_reference;

    @ViewById(R.id.vote_description)
    TextView vote_description;

    @ViewById(R.id.ll_candidats)
    LinearLayout ll_candidats;

    @ViewById(R.id.lv_jm_candidats)
    ListView lv_jm_candidats;

    @Bean
    JMParticipationListAdapter adapter;

    @RestService
    VOTSocialChoiceAPI serviceAPI;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SCMajorityJudgment.class);

        System.out.println(" obj : " + socialChoice);

        // Action bar settings
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle(socialChoice.getTitle());

        tv_reference.setText(TV_STRING);
        vote_description.setMovementMethod(new ScrollingMovementMethod());
        vote_description.setText(socialChoice.getDescription());

        Stream.of(socialChoice.getCandidats())
                .forEach(candidat -> candidat.setLabels(socialChoice.getData().getLabels()));

        adapter.getItems().addAll(socialChoice.getCandidats());
        lv_jm_candidats.setAdapter(adapter);
    }
    // TODO: COMMENT

    /**
     * Confirm the vote and send it via HTTP Request
     */
    @OptionsItem(R.id.participation_action_check)
    public void onClickCheckmark() {
        Vote vote = new Vote(socialChoice.getId());

        Log.d(TAG, String.valueOf(socialChoice.getCandidats()));
        Stream.of(socialChoice.getCandidats())
                .forEach(candidat -> vote.put(candidat.getName(), candidat.getLabelSelected()));
        Log.d(TAG, String.valueOf(vote));

        sendVote(vote);
    }

    /**
     * Go back when click go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow() {
        backDone();
    }

    // TODO: COMMENT

    /**
     * Send vote to VOT System.
     *
     * @param vote vote values
     */
    @Background
    void sendVote(Vote vote) {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<JsonObject>> response = serviceAPI.vote(vote);
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful())
                backDone();
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
            if (NetworkUtils.is403Error(e)) {
                Snack.showFailureMessage(getWindow().getDecorView().findViewById(android.R.id.content),
                        getString(R.string.snack_error_http_participation_is_exist),
                        Snackbar.LENGTH_LONG);
            } else
                Snack.showFailureMessage(getWindow().getDecorView().findViewById(android.R.id.content),
                        getString(R.string.snack_error_http_sending),
                        Snackbar.LENGTH_LONG);
        }
    }

    @UiThread
    void backDone() {
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }
}
