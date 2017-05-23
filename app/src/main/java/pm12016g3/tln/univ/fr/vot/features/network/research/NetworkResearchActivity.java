package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.
 * File NetworkActivity.java.
 * Created by Ysee on 15/05/2017 - 09:41.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EActivity(R.layout.network_network_research_activity)
@OptionsMenu(R.menu.network_research_activity_bar)
public class NetworkResearchActivity extends AppCompatActivity {
    private final String TAG = NetworkResearchActivity.class.getSimpleName();

    @ViewById(R.id.network_research_input_research)
    EditText research;

    @ViewById(R.id.network_research_persons_list)
    ListView personsListView;


    @Bean
    NetworkResearchListAdapter adapter;

    List<BasicItem> allPersons = new ArrayList<>();

    @AfterViews
    void init() {

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        allPersons.add(new BasicItem("John"));
        allPersons.add(new BasicItem("Paul"));
        allPersons.add(new BasicItem("Jack"));
        adapter.addAll(allPersons);
        personsListView.setAdapter(adapter);

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

    @ItemClick(R.id.network_research_persons_list)
    void personListItemClicked(BasicItem item) {
        Log.d(TAG, "Item clicked... " + item.toString());
        item.setSelected(!item.isSelected());
        adapter.notifyDataSetChanged();
    }

    @TextChange(R.id.network_research_input_research)
    void onTextChangesOnHelloTextView(CharSequence text,
                                      TextView hello,
                                      int before,
                                      int start,
                                      int count) {
        adapter.clear();
        if(text.length() == 0){
            adapter.addAll(allPersons);
        }else if(text.length() !=0){
            for (BasicItem person : allPersons) {
                if (person.getTitle().toLowerCase().startsWith(text.toString().toLowerCase())) {
                    adapter.add(person);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

}
