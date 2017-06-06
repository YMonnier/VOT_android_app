package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Stream;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchActivity_;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;

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
    List<User> allFriends = new ArrayList<>();

    /**
     * Progress view
     */
    LoaderDialog progressView;

    @RestService
    VOTFriendsAPI serviceAPI;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        progressView = new LoaderDialog(getActivity(), "");
        friendListView.setTextFilterEnabled(true);
        loadData();
    }

    /**
     * Listen to research EditText text change then update ListView display
     *
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
            Stream.of(allFriends)
                    .filter(friend -> friend.getPseudo().toLowerCase().startsWith(text.toString().toLowerCase()))
                    .forEach(friend -> adapter.add(friend));
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

    /**
     * Function allowing to get information
     * from server and put them to the adapter
     * <p>
     * This task is done into the background thread.
     */
    @Background
    void loadData() {
        try {
            showProgress();
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<List<User>>> response = serviceAPI.getFriends();
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                List<User> users = response.getBody().getData();
                Log.d(TAG, Arrays.toString(users.toArray()));
                allFriends.addAll(users);
                adapter.getItems()
                        .addAll(users);

                setAdapter();
                dismissProgress();
            } else {
                dismissProgress();
            }
        } catch (RestClientException e) {
            dismissProgress();
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    /**
     * Show the progress view.
     * This task is done on UI Thread.
     */
    @UiThread
    void showProgress() {
        progressView.show();
    }

    /**
     * Dismiss the progress view.
     * This task is done on UI Thread.
     */
    @UiThread
    void dismissProgress() {
        progressView.dismiss();
    }

    /**
     * Set adapter
     * This task is done on UI Thread.
     */
    @UiThread
    void setAdapter() {
        friendListView.setAdapter(adapter);
    }
}
