package pm12016g3.tln.univ.fr.vot.features.network.notification;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.JsonObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.network.notification.custom.OnClickRequestListener;
import pm12016g3.tln.univ.fr.vot.models.realm.Request;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EActivity(R.layout.show_friend_invitation_activity)
public class ShowFriendInvitationActivity extends AppCompatActivity
        implements RealmChangeListener<RealmResults<Request>>, OnClickRequestListener {
    private final String TAG = ShowFriendInvitationActivity.class.getSimpleName();

    /**
     * Realm Database Instance
     */
    Realm realm;

    /**
     * Requests list from Realm.
     */
    RealmResults<Request> results;


    @ViewById(R.id.friend_invitation_lv)
    ListView invitationListView;

    /**
     * ListAdapter for friend invitation ListView
     */
    @Bean
    ShowFriendInvitationListAdapter adapter;

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
        adapter.setListener(this);
        setAdapter();
        loadData();
        progressView = new LoaderDialog(this, "");
    }

    private void loadData() {
        realm = Realm.getDefaultInstance();
        results = realm.where(Request.class).findAll();
        results.addChangeListener(this);
        adapter.addAll(results);
        adapter.notifyDataSetChanged();
    }

    private void update() {
        adapter.clear();
        results = realm.where(Request.class).findAll();
        adapter.addAll(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        results.removeChangeListener(this);
        results = null;
    }

    /**
     * Called when a transaction is committed.
     *
     * @param element Request raw.
     */
    @Override
    public void onChange(RealmResults<Request> element) {
        if (adapter != null) {
            Log.d(TAG, "Database changed!!");
            update();
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Go back when click go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow() {
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    @Override
    public void onConfirm(Request request) {
        answer(request.getId(), true);
    }

    @Override
    public void onDecline(Request request) {
        answer(request.getId(), false);
    }

    @Background
    void answer(Long id, final boolean answer) {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<JsonObject> response = serviceAPI.answer(id, answer);
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                Realm realm = Realm.getDefaultInstance();
                Request req = realm.where(Request.class)
                        .equalTo("id", id)
                        .findFirst();
                if (req != null) {
                    realm.beginTransaction();
                    req.deleteFromRealm();
                    realm.commitTransaction();
                }
            } else {
                answer(id, answer);
            }
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
        invitationListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
