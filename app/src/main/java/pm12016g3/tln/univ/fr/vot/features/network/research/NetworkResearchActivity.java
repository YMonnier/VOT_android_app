package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.network.research.custom.SendOnClickListener;
import pm12016g3.tln.univ.fr.vot.models.FriendRequest;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EActivity(R.layout.network_network_research_activity)
public class NetworkResearchActivity extends AppCompatActivity
        implements SendOnClickListener<User> {
    private final String TAG = NetworkResearchActivity.class.getSimpleName();
    /**
     * EditText to Research Persons
     */
    @ViewById(R.id.network_research_input_research)
    EditText research;

    /**
     * ListView to show Researched Persons
     */
    @ViewById(R.id.network_research_persons_list)
    ListView personsListView;

    /**
     * Adapter for ListView
     */
    @Bean
    NetworkResearchListAdapter adapter;

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
        adapter.setListener(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        progressView = new LoaderDialog(this, "");
    }


    /**
     * Go back when click go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow() {
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    /**
     * Listen to Person Item click in the ListView
     *
     * @param item
     */
    @ItemClick(R.id.network_research_persons_list)
    void personListItemClicked(BasicItem item) {
        Log.d(TAG, "Item clicked... " + item.toString());
        item.setSelected(!item.isSelected());
        adapter.notifyDataSetChanged();
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
    @TextChange(R.id.network_research_input_research)
    void onTextChanges(CharSequence text,
                       TextView hello,
                       int before,
                       int start,
                       int count) {
        if (text != null)
            searchOnAPI(text.toString());
    }

    /**
     * Function allowing to get information
     * from server and put them to the adapter.
     * <p>
     * The user can find user by writting his nickname.
     * <p>
     * This task is done into the background thread.
     *
     * @param pattern string pattern.
     */
    @Background
    void searchOnAPI(final String pattern) {
        clearAdapter();
        try {
            Log.d(TAG, Settings.currentUser.getAccessToken());
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<List<User>>> users = serviceAPI.findUserByPseudo(pattern);
            adapter.getItems().addAll(users.getBody().getData());

            setAdapter();
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
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
        personsListView.setAdapter(adapter);
    }

    /**
     * Clear adapter
     * This task is done on UI Thread.
     */
    @UiThread
    void clearAdapter() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    /**
     * Custom on Click listner
     *
     * @param item item clicked.
     */
    @Override
    public void onClick(User item) {
        Log.d(TAG, "Item clicked: " + item);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.add(item.getId());
        send(friendRequest);
    }

    /**
     * Send a friend request
     * @param friendRequest friend request
     */
    @Background
    void send(FriendRequest friendRequest) {
        try {
            Response response = serviceAPI.requests(friendRequest);
            Log.d(TAG, response.toString());
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
