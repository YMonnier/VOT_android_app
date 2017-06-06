package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withoutOrder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.google.gson.Gson;
import com.woxthebox.draglistview.DragListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.Vote;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTVoteAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_participation_simple_vote_without_order_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class SimpleVoteWithoutOrderParticipationActivity extends AppCompatActivity {

    final String TAG = SimpleVoteWithoutOrderParticipationActivity.class.getSimpleName();
    private final String TV_STRING1 = "Vous pouvez selectionner jusqu'à ";
    private final String TV_STRING2 = " choix \nVotre choix :";

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
    VOTVoteAPI apiService;

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

        System.out.println(" obj : "+socialChoice);

        this.setTitle(socialChoice.getTitle());

        tv_reference.setText(TV_STRING1+socialChoice.getData().getNbChoice()+TV_STRING2);
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
    public void onClickCheckmark(){

        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("1", "a");
        Gson gson = new Gson();

        /*for (SimpleVoteWithoutOrderParticipationItem simpleVoteWithoutOrderParticipationItem : listAdapter.getItemList()) {
            if (simpleVoteWithoutOrderParticipationItem.isChecked()) {
                Candidat candidat = new Candidat();
                candidat.setName(simpleVoteWithoutOrderParticipationItem.getChoice_title());
                list.add(candidat);
            }

        }*/

        List<Candidat> candidatsSelected = Stream.of(listAdapter.getItemList())
                .filter(value -> value.isSelected())
                .toList();
        Vote vote = new Vote(socialChoice.getId());
        Stream.of(candidatsSelected)
                .forEach(candidat -> vote.put(candidat.getName()));
        Log.d(TAG, "Vote anwser: " + vote);


        /*try {
            ResponseEntity<Response<String>> response = apiService.insert(vote);
            Log.i(TAG, response.toString());

        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }*/

        //ResponseEntity<Response<String>> response = apiService.insert(vote);
        //Log.d(TAG, "Vote resposne: " + response);
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        Log.d(TAG,listAdapter.getItemList().toString());
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

}
