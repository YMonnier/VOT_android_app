package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.
 * File NetworkActivity.java.
 * Created by Ysee on 15/05/2017 - 09:41.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EActivity(R.layout.network_network_research_activity)
public class NetworkResearchActivity extends AppCompatActivity {
    private final String TAG = NetworkResearchActivity.class.getSimpleName();

    @ViewById(R.id.network_research_input_research)
    EditText research;

    @ViewById(R.id.network_research_persons_list)
    ListView personsListView;

    @Bean
    NetworkResearchListAdapter adapter;

    List<String> allPersons = new ArrayList<>();
    List<String> filteredPersons = new ArrayList<>();

    @AfterViews
    void init() {

        adapter
                .add(new NetworkResearchItem("John"))
                .add(new NetworkResearchItem("Paul"))
                .add(new NetworkResearchItem("Jack"));

        personsListView.setAdapter(adapter);
        //filteredPersons.addAll(allPersons);

        /*adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.network_friend_list_item,
                filteredPersons);
        personsListView.setAdapter(adapter);*/
    }


    @ItemClick(R.id.network_research_persons_list)
    void personListItemClicked(NetworkResearchItem item) {
        Log.d(TAG, "Item clicked... " + item.toString());
        item.setSelected(!item.isSelected());
        adapter.notifyDataSetChanged();
    }
/*
    @TextChange(R.id.network_input_research)
    void onTextChangesOnHelloTextView(CharSequence text,
                                      TextView hello,
                                      int before,
                                      int start,
                                      int count) {
        if(text.length() == 0){
            filteredPersons.clear();
            filteredPersons.addAll(allPersons);
        }else if(text.length() !=0){
            filteredPersons.clear();
            for (String name : allPersons) {
                if (name.startsWith(text.toString())) {
                    filteredPersons.add(name);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
*/

}
