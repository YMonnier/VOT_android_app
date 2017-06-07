package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.woxthebox.draglistview.DragItem;
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
import pm12016g3.tln.univ.fr.vot.models.shared.SCSimpleTransfarableVote;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
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
    List<Candidat> candidats;

    SocialChoice<SCSimpleTransfarableVote> socialChoice;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        Gson gson = GsonSingleton.getInstance();
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SCSimpleTransfarableVote.class);
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
    public void onClickCheckmark(){
        listAdapter.getItemList();
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
}
