package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.Vote;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSimpleTransfarableVote;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_participation_stv_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class STVParticipationActivity extends AppCompatActivity {

    final String TAG = STVParticipationActivity.class.getSimpleName();

    /**
     * DragListView that contains the choices
     */
    @ViewById(R.id.stv_participation_draglistview)
    DragListView choiceListView;

    @ViewById(R.id.vote_description)
    TextView description;

    /**
     * Adapter for DragListView
     */

    STVParticipationListAdapter listAdapter;

    /**
     * A list of Participation Item object
     */
    List<Candidat> candidats = null;

    SocialChoice<SCSimpleTransfarableVote> socialChoice;

    Gson gson;


    @RestService
    VOTSocialChoiceAPI serviceAPI;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        //Init Toobar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get SocialChoice from Intent
        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        gson = GsonSingleton.getInstance();
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SCSimpleTransfarableVote.class);

        //Setting params
        getSupportActionBar().setTitle(socialChoice.getTitle());
        description.setText(socialChoice.getDescription());
        candidats = socialChoice.getCandidats();

        choiceListView.getRecyclerView().setVerticalScrollBarEnabled(true);
        choiceListView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new STVParticipationListAdapter(candidats,
                R.layout.consult_participation_stv_participation_item,
                R.id.stv_participation_item,
                false);
        choiceListView.setAdapter(listAdapter, true);
        choiceListView.setCanDragHorizontally(false);
        choiceListView.setCustomDragItem(new MyDragItem(this,
                R.layout.consult_participation_stv_participation_item));


    }

    /**
     * Listen to the click of the check button on the menu bar
     */
    @OptionsItem(R.id.participation_action_check)
    public void onClickCheckmark() {
        candidats = listAdapter.getItemList();
        Vote vote = new Vote(socialChoice.getId());

        candidats
                .forEach(candidat -> vote.put(String.valueOf(candidats.indexOf(candidat) + 1), candidat.getName()));
        String voteJson = gson.toJson(vote);
        Log.d(TAG, "voteJson " + voteJson);
        sendVote(vote);
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
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
     * A custom drag item provided to change the visual appearance of the dragging item.
     */
    private static class MyDragItem extends DragItem {

        MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence title = ((TextView) clickedView.
                    findViewById(R.id.stv_participation_choice_title))
                    .getText();
            ((TextView) dragView.findViewById(R.id.stv_participation_choice_title)).setText(title);
            dragView.findViewById(R.id.stv_participation_item).setBackgroundColor(dragView.getResources().getColor(R.color.vot_brown));
        }
    }

    /**
     * @param vote
     */
    @Background
    void sendVote(Vote vote) {
        try {
            Log.d(TAG, "Sending Vote....");
            Log.d(TAG, Settings.currentUser.getAccessToken());
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<JsonObject>> response = serviceAPI.vote(vote);
            Log.d(TAG, response.getHeaders().toString());
            Log.d(TAG, response.toString());
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
