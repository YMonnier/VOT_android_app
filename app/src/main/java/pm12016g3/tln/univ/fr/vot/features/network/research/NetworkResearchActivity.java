package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EActivity(R.layout.network_network_research_activity)
public class NetworkResearchActivity extends AppCompatActivity {
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
     * List of persons
     */
    List<User> allPersons = new ArrayList<>();

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

        searchOnAPI(text.toString());
        /*if (text.length() == 0) {
            adapter.addAll(allPersons);
        } else if (text.length() != 0) {
            for (BasicItem person : allPersons) {
                if (person.getTitle().toLowerCase().startsWith(text.toString().toLowerCase())) {
                    adapter.add(person);
                }
            }
        }*/

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
}
