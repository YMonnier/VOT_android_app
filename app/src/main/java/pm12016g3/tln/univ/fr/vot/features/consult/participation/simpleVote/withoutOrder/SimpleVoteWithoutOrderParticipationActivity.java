package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withoutOrder;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.woxthebox.draglistview.DragListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.consult.create.Validable;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.Vote;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_participation_simple_vote_without_order_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class SimpleVoteWithoutOrderParticipationActivity extends AppCompatActivity
        implements Validable {

    final String TAG = SimpleVoteWithoutOrderParticipationActivity.class.getSimpleName();
    private final String TV_STRING1 = "Vous pouvez selectionner jusqu'à ";
    private final String TV_STRING2 = " choix \nLes choix :";

    SocialChoice<SCSMajorityBallot> socialChoice;

    @ViewById(R.id.tv_reference)
    TextView tv_reference;

    @ViewById(R.id.vote_description)
    TextView vote_description;

    /**
     * DragListView that contains the choices
     */
    @ViewById(R.id.sv_participation_draglistview)
    DragListView choiceListView;

    /**
     * Rest service to get
     * information from server.
     */
    @RestService
    VOTSocialChoiceAPI serviceAPI;

    /**
     * Adapter for DragListView
     */
    SimpleVoteWithoutOrderParticipationListAdapter listAdapter;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {

        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        Gson gson = GsonSingleton.getInstance();
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SCSMajorityBallot.class);

        System.out.println(" obj : " + socialChoice);

        this.setTitle(socialChoice.getTitle());

        tv_reference.setText(TV_STRING1 + socialChoice.getData().getNbChoice() + TV_STRING2);
        vote_description.setMovementMethod(new ScrollingMovementMethod());
        vote_description.setText(socialChoice.getDescription());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        choiceListView.getRecyclerView().setVerticalScrollBarEnabled(true);

        choiceListView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new SimpleVoteWithoutOrderParticipationListAdapter(
                R.layout.consult_participation_simple_vote_with_order_participation_item,
                R.id.sv_participation_choice_title,
                true,
                socialChoice);

        choiceListView.setAdapter(listAdapter, true);
        choiceListView.setCanDragHorizontally(false);

    }

    /**
     * Listen to the click of the check button on the menu bar
     */
    @OptionsItem(R.id.participation_action_check)
    public void onClickCheckmark() {
        if (validate()) {
            List<Candidat> candidatsSelected = Stream.of(listAdapter.getItemList())
                    .filter(Candidat::isSelected)
                    .toList();

            int count = 0;
            Vote vote = new Vote(socialChoice.getId());

            for (Candidat candidat : candidatsSelected) {
                count += 1;
                vote.put(String.valueOf(count), candidat.getName());
            }

            sendVote(vote);
        } else {
            final String ERROR_MESSAGE = "Veuillez selectionner/lister " + socialChoice.getData().getNbChoice() + " gagnants";
            Snack.showFailureMessage(getWindow().getDecorView().findViewById(android.R.id.content),
                    ERROR_MESSAGE,
                    Snackbar.LENGTH_LONG);
        }
    }

    /**
     * Go back when click go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow() {
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    /**
     * Send a vote to the database
     *
     * @param vote vote Object
     */
    @Background
    void sendVote(Vote vote) {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<JsonObject>> response = serviceAPI.vote(vote);
            showParticipationDialog(response);
            if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                Snack.showFailureMessage(getWindow().getDecorView().findViewById(android.R.id.content),
                        getString(R.string.snack_error_http_400_500),
                        Snackbar.LENGTH_LONG);
            } else {
                Log.d(TAG, "Vote anwser: " + vote);
                backDone();
            }

            Log.d(TAG, response.toString());
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    @UiThread
    void backDone() {
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    void showParticipationDialog(ResponseEntity response){
        if(response.getStatusCode().is2xxSuccessful()) {
            MainActivity_.showParticipationDialog(getApplicationContext(), "Envoie d'un vote", "A voté !");
        }else{
            MainActivity_.showParticipationDialog(getApplicationContext(), "Envoie d'un vote", "Vous avez déjà voté !");
        }
    }

    /**
     * Validate the current form.
     *
     * @return true if the form is valid, otherwise false.
     */
    @Override
    public boolean validate() {
        return Stream.of(listAdapter.getItemList())
                .filter(Candidat::isSelected)
                .toList().size() == socialChoice.getData().getNbChoice();
    }

    /**
     * Set data to the parent model.
     */
    @Override
    public void setData() {
    }
}
