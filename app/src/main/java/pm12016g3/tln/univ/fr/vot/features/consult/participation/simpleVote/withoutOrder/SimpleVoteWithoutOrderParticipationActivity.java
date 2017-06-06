package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withoutOrder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.woxthebox.draglistview.DragListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
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
     * Adapter for DragListView
     */

    SimpleVoteWithoutOrderParticipationListAdapter listAdapter;

    /**
     * A list of Participation Item object
     */
    List<SimpleVoteWithoutOrderParticipationItem> choices;

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
            choices.add(new SimpleVoteWithoutOrderParticipationItem(candidat.getName()));

        choiceListView.getRecyclerView().setVerticalScrollBarEnabled(true);

        choiceListView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new SimpleVoteWithoutOrderParticipationListAdapter(choices,
                R.layout.consult_participation_simple_vote_with_order_participation_item,
                R.id.sv_participation_choice_title, true, socialChoice);
        choiceListView.setAdapter(listAdapter, true);
        choiceListView.setCanDragHorizontally(false);

    }

    /**
     * Listen to the click of the check button on the menu bar
     */
    @OptionsItem(R.id.participation_action_check)
    public void onClickCheckmark(){
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
