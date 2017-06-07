package pm12016g3.tln.univ.fr.vot.features.network.notification;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

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
