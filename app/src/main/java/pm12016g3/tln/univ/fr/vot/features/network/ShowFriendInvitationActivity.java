package pm12016g3.tln.univ.fr.vot.features.network;

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
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchListAdapter;
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

@EActivity(R.layout.show_friend_invitation_activity)
public class ShowFriendInvitationActivity extends AppCompatActivity {
    private final String TAG = ShowFriendInvitationActivity.class.getSimpleName();

    @ViewById(R.id.friend_invitation_lv)
    ListView invitationListView;

    /**
     * ListAdapter for friend invitation ListView
     */
    @Bean
    ShowFriendinvitationListAdapter adapter;

    /**
     * Progress view
     */
    LoaderDialog progressView;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        adapter.add(new User.Builder()
                .setPseudo("John")
                .build())
                .add(new User.Builder()
                        .setPseudo("Paul")
                        .build());
        setAdapter();
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
