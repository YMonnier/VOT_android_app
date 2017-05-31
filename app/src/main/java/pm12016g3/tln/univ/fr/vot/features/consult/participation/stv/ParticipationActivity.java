package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragItemAdapter;
import com.woxthebox.draglistview.DragListView;
import com.woxthebox.draglistview.swipe.ListSwipeHelper;
import com.woxthebox.draglistview.swipe.ListSwipeItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

@EActivity(R.layout.consult_participation_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class ParticipationActivity extends AppCompatActivity {

    final String TAG = ParticipationActivity.class.getSimpleName();


    @ViewById(R.id.participation_draglistview)
    DragListView choiceListView;

    ParticipationListAdapter listAdapter;

    List<ParticipationItem> choices;

    @AfterViews
    void init() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        choices = new ArrayList<>();
        choices.add(new ParticipationItem("jon"));
        choices.add(new ParticipationItem("helo"));
        choices.add(new ParticipationItem("cuda"));
        choices.add(new ParticipationItem("dada"));

        choiceListView.getRecyclerView().setVerticalScrollBarEnabled(true);

        choiceListView.setDragListListener(new DragListView.DragListListenerAdapter() {
            @Override
            public void onItemDragStarted(int position) {

                Toast.makeText(choiceListView.getContext(), "Start - position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {

                if (fromPosition != toPosition) {
                    Toast.makeText(choiceListView.getContext(), "End - position: " + toPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });

        choiceListView.setLayoutManager(new LinearLayoutManager(this));
        ParticipationListAdapter listAdapter = new ParticipationListAdapter(choices, R.layout.consult_participation_participation_item, R.id.participation_item_choice, false);
        choiceListView.setAdapter(listAdapter, true);
        choiceListView.setCanDragHorizontally(false);
        choiceListView.setCustomDragItem(new MyDragItem(this, R.layout.consult_participation_participation_item));

    }


    @OptionsItem(R.id.network_research_action_check)
    public void onClickCheckmark(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

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
