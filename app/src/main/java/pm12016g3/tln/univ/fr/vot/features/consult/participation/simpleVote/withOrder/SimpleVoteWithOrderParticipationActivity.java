package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withOrder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.annimon.stream.Stream;
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

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withoutOrder.SimpleVoteWithoutOrderParticipationItem;
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
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_participation_simple_vote_with_order_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class SimpleVoteWithOrderParticipationActivity extends AppCompatActivity {

    final String TAG = SimpleVoteWithOrderParticipationActivity.class.getSimpleName();
    private final String TV_STRING1 = "Vous devez faire un classement.\nVous pouvez selectionner jusqu'à ";
    private final String TV_STRING2 = " choix \nLes choix :";

    SocialChoice<SCSMajorityBallot> socialChoice;

    @ViewById(R.id.tv_reference)
    TextView tv_reference;

    @ViewById(R.id.vote_description)
    TextView vote_description;

    /**
     * Rest service to get
     * information from server.
     */
    @RestService
    VOTSocialChoiceAPI serviceAPI;

    /**
     * DragListView that contains the choices
     */
    @ViewById(R.id.sv_participation_draglistview)
    DragListView choiceListView;

    /**
     * Adapter for DragListView
     */

    SimpleVoteWithOrderParticipationListAdapter listAdapter;

    /**
     * A list of Participation Item object
     */
    List<SimpleVoteWithOrderParticipationItem> choices;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {

        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        Gson gson = GsonSingleton.getInstance();
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SCSMajorityBallot.class);

        System.out.println(" obj : "+socialChoice);

        this.setTitle(socialChoice.getTitle());

        tv_reference.setText(TV_STRING1+socialChoice.getData().getNbChoice()+TV_STRING2);
        vote_description.setText(socialChoice.getDescription());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        choices = new ArrayList<>();

        for (Candidat candidat : socialChoice.getCandidats())
            choices.add(new SimpleVoteWithOrderParticipationItem(candidat.getName()));

        choiceListView.getRecyclerView().setVerticalScrollBarEnabled(true);

        choiceListView.setLayoutManager(new LinearLayoutManager(this));

        listAdapter = new SimpleVoteWithOrderParticipationListAdapter(choices,
                R.layout.consult_participation_simple_vote_with_order_participation_item,
                R.id.sv_participation_item_choice, false, socialChoice);
        choiceListView.setAdapter(listAdapter, true);

        choiceListView.setCanDragHorizontally(false);
        choiceListView.setCustomDragItem(new MyDragItem(this,
                R.layout.consult_participation_simple_vote_with_order_participation_item));

    }

    /**
     * Listen to the click of the check button on the menu bar
     */
    @OptionsItem(R.id.participation_action_check)
    public void onClickCheckmark(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        Log.d(TAG,listAdapter.getItemList().toString());

        List<SimpleVoteWithOrderParticipationItem> candidatsSelected = Stream.of(listAdapter.getItemList())
                .filter(value -> value.isChecked())
                .toList();

        Vote vote = new Vote(socialChoice.getId());
        System.out.println("lalala  : "+candidatsSelected);

        for (SimpleVoteWithOrderParticipationItem candidat : candidatsSelected) {
            vote.put(String.valueOf(candidat.getOrder()), candidat.getChoice_title());
        }

        sendVote(vote);

        finish();
    }

    /**
     * Go back when click go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    /**
     * Send a vote to the database
     * @param vote
     */
    @Background
    void sendVote(Vote vote) {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<JsonObject>> response = serviceAPI.vote(vote);
            Log.d(TAG, response.toString());
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    /**
     * A custom drag item provided to change
     * the visual appearance of the dragging item.
     */
    private static class MyDragItem extends DragItem {

        MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
           /* CharSequence id = ((TextView) clickedView.findViewById(R.id.participation_choice_id)).getText();
            ((TextView) dragView.findViewById(R.id.participation_choice_id)).setText(id);*/
            CharSequence title = ((TextView) clickedView.findViewById(R.id.sv_participation_choice_title)).getText();
            ((TextView) dragView.findViewById(R.id.sv_participation_choice_title)).setText(title);
            boolean check = ((CheckedTextView) clickedView.findViewById(R.id.sv_participation_choice_check_tv)).isChecked();
            ((CheckedTextView)dragView.findViewById(R.id.sv_participation_choice_check_tv)).setChecked(check);
            dragView.findViewById(R.id.sv_participation_item).setBackgroundColor(dragView.getResources().getColor(R.color.vot_brown));
        }
    }
}
