package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchActivity_;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EFragment(R.layout.network_network_fragment)
public class NetworkFragment extends Fragment {
    private final String TAG = NetworkFragment.class.getSimpleName();
    /**
     * EditText to Research friends
     */
    @ViewById(R.id.network_input_research)
    EditText research;

    /**
     * ListView to show the friends
     */
    @ViewById(R.id.network_friend_list)
    ListView friendListView;

    /**
     * Floating action button to add friend
     */
    @ViewById(R.id.fabAdd)
    FloatingActionButton fabAdd;

    /**
     * ListAdapter for friends ListView
     */
    @Bean
    NetworkFragmentListAdapter adapter;

    /**
     * List of friends
     */
    List<BasicItem> allFriends = new ArrayList<>();

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        allFriends.add(new BasicItem("Donut"));
        allFriends.add(new BasicItem("Eclair"));
        allFriends.add(new BasicItem("Lollipop"));
        allFriends.add(new BasicItem("Count"));
        allFriends.add(new BasicItem("Coucou"));
        adapter.addAll(allFriends);
        friendListView.setAdapter(adapter);
        friendListView.setTextFilterEnabled(true);
    }

    /**
     * Listen to research EditText text change then update ListView display
     * @param text
     * @param hello
     * @param before
     * @param start
     * @param count
     */
    @TextChange(R.id.network_input_research)
    void onTextChanges(final CharSequence text,
                                      TextView hello,
                                      int before,
                                      int start,
                                      int count) {
       adapter.clear();
        if (text.length() == 0) {
            adapter.addAll(allFriends);
        } else if (text.length() != 0) {
            for (BasicItem friend : allFriends) {
                if (friend.getTitle().toLowerCase().startsWith(text.toString().toLowerCase())) {
                    adapter.add(friend);
                }
            }
        }
        adapter.notifyDataSetChanged();


    }

    /**
     * Listen to floating action button click then jump to researching friends page
     */
    @Click(R.id.fabAdd)
    public void fabClick() {
        getActivity()
                .startActivity(new Intent(getActivity(), NetworkResearchActivity_.class));

    }
}
