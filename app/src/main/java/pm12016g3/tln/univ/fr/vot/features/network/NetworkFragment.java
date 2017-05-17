package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
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

@EFragment(R.layout.network_network_fragment)
public class NetworkFragment extends Fragment {
    private final String TAG = NetworkFragment.class.getSimpleName();

    @ViewById(R.id.network_input_research)
    EditText research;

    @ViewById(R.id.network_friend_list)
    ListView friendListView;

    @ViewById(R.id.fabAdd)
    FloatingActionButton fabAdd;

    List<String> allFriends = new ArrayList<>();
    List<String> filteredFriends = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public NetworkFragment() {
    }

    @AfterViews
    void init() {
        allFriends.add("Donut");
        allFriends.add("Eclair");
        allFriends.add("Lollipop");
        allFriends.add("Cat");
        allFriends.add("Count");
        allFriends.add("Claire");
        allFriends.add("Cliare Man");
        allFriends.add("Clairee");

        filteredFriends.addAll(allFriends);

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.network_friend_list_item,
                filteredFriends);
        friendListView.setAdapter(adapter);
    }

    @TextChange(R.id.network_input_research)
    void onTextChangesOnHelloTextView(CharSequence text,
                                      TextView hello,
                                      int before,
                                      int start,
                                      int count) {
        if(text.length() == 0){
            filteredFriends.clear();
            filteredFriends.addAll(allFriends);
        }else if(text.length() !=0){
            filteredFriends.clear();
            for (String name : allFriends) {
                if (name.startsWith(text.toString())) {
                    filteredFriends.add(name);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
