package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
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

@EFragment(R.layout.network_network_research_fragment)
public class NetworkResearchFragment extends Fragment {
    private final String TAG = NetworkResearchFragment.class.getSimpleName();

    @ViewById(R.id.network_research_input_research)
    EditText research;

    @ViewById(R.id.network_research_persons_list)
    ListView personsListView;


    List<String> allPersons = new ArrayList<>();
    List<String> filteredPersons = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public NetworkResearchFragment() {
    }

    @AfterViews
    void init() {
        allPersons.add("Dark");
        allPersons.add("Green");
        allPersons.add("Light");
        allPersons.add("Chacha");


        filteredPersons.addAll(allPersons);

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.network_friend_list_item,
                filteredPersons);
        personsListView.setAdapter(adapter);
    }

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


}
