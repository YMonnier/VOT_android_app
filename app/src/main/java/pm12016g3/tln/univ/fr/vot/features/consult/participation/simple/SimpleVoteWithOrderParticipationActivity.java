package pm12016g3.tln.univ.fr.vot.features.consult.participation.simple;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

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
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_participation_simple_vote_with_order_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class SimpleVoteWithOrderParticipationActivity extends AppCompatActivity {

    final String TAG = SimpleVoteWithOrderParticipationActivity.class.getSimpleName();

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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        choices = new ArrayList<>();
        choices.add(new SimpleVoteWithOrderParticipationItem("jon"));
        choices.add(new SimpleVoteWithOrderParticipationItem("helo"));
        choices.add(new SimpleVoteWithOrderParticipationItem("cuda"));
        choices.add(new SimpleVoteWithOrderParticipationItem("dada"));

        choiceListView.getRecyclerView().setVerticalScrollBarEnabled(true);

        choiceListView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new SimpleVoteWithOrderParticipationListAdapter(choices,
                R.layout.consult_participation_simple_vote_with_order_participation_item, R.id.participation_item_choice, false);
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
           /* CharSequence id = ((TextView) clickedView.findViewById(R.id.participation_choice_id)).getText();
            ((TextView) dragView.findViewById(R.id.participation_choice_id)).setText(id);*/
            CharSequence title = ((TextView) clickedView.findViewById(R.id.participation_choice_title)).getText();
            ((TextView) dragView.findViewById(R.id.participation_choice_title)).setText(title);
            boolean check = ((CheckedTextView) clickedView.findViewById(R.id.participation_choice_check_tv)).isChecked();
            ((CheckedTextView)dragView.findViewById(R.id.participation_choice_check_tv)).setChecked(check);
            dragView.findViewById(R.id.participation_item).setBackgroundColor(dragView.getResources().getColor(R.color.vot_brown));
        }
    }
}
