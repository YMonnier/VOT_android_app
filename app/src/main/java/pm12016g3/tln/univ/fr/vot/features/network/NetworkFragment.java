package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Stream;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchActivity_;

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

    @Bean
    NetworkFragmentListAdapter adapter;

    List<NetWorkFragmentItem> allFriends = new ArrayList<>();

    @AfterViews
    void init() {
        allFriends.add(new NetWorkFragmentItem("Donut"));
        allFriends.add(new NetWorkFragmentItem("Eclair"));
        allFriends.add(new NetWorkFragmentItem("Lollipop"));
        allFriends.add(new NetWorkFragmentItem("Count"));
        allFriends.add(new NetWorkFragmentItem("Coucou"));
        adapter.addAll(allFriends);
        friendListView.setAdapter(adapter);
        friendListView.setTextFilterEnabled(true);
    }

    @TextChange(R.id.network_input_research)
    void onTextChangesOnHelloTextView(final CharSequence text,
                                      TextView hello,
                                      int before,
                                      int start,
                                      int count) {
       adapter.clear();
        if (text.length() == 0) {
            adapter.addAll(allFriends);
        } else if (text.length() != 0) {
            for (NetWorkFragmentItem friend : allFriends) {
                if (friend.getTitle().toLowerCase().startsWith(text.toString().toLowerCase())) {
                    adapter.add(friend);
                }
            }
        }
        adapter.notifyDataSetChanged();


    }

    @Click(R.id.fabAdd)
    public void fabClick() {
        getActivity()
                .startActivity(new Intent(getActivity(), NetworkResearchActivity_.class));

    }
}
