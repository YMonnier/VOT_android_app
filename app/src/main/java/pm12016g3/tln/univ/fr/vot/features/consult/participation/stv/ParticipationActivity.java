package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

@EActivity(R.layout.consult_participation_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class ParticipationActivity extends AppCompatActivity {
    final String TAG = ParticipationActivity.class.getSimpleName();
    @ViewById(R.id.participation_choice_order_listview)
    ListView choiceListView;


    @Bean
    ParticipationListAdapter adapter;

    List<ParticipationItem> choices = new ArrayList<>();

    @AfterViews
    void init() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        choices.add(new ParticipationItem("1","A"));
        choices.add(new ParticipationItem("2","B"));
        choices.add(new ParticipationItem("3","C"));
        adapter.setItems(choices);
        choiceListView.setAdapter(adapter);
    }
    
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    @OptionsItem(R.id.network_research_action_check)
    public void onClickCheckmark(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }


}
