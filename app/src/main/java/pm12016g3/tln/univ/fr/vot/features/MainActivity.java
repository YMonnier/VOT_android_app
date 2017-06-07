package pm12016g3.tln.univ.fr.vot.features;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.about.AboutUsFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.consult.ConsultFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment_;
import pm12016g3.tln.univ.fr.vot.features.network.NetworkFragment_;
import pm12016g3.tln.univ.fr.vot.features.notification.NotificationBroadcastManager;
import pm12016g3.tln.univ.fr.vot.features.root.LoginActivity;
import pm12016g3.tln.univ.fr.vot.features.root.LoginActivity_;
import pm12016g3.tln.univ.fr.vot.features.statistic.StatisticFragment_;
import pm12016g3.tln.univ.fr.vot.models.realm.Request;

@EActivity(R.layout.main_activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private GoogleApiClient googleApiClient;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    /**
     * Broadcast Manager
     */
    @Bean
    NotificationBroadcastManager notificationBroadcastManager;

    @AfterViews
    void init() {
        googleApiClient = LoginActivity.googleApiClient;
        if (!googleApiClient.isConnected())
            googleApiClient.connect();

        setSupportActionBar(toolbar);
        setFragment(new ConsultFragment_(), getString(R.string.sidebar_consult));

        View headerLayout = navigationView.getHeaderView(0);
        TextView userNameView = (TextView) headerLayout.findViewById(R.id.user_name);
        userNameView.setText(Settings.currentUser.getPseudo());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @AfterInject
    void initAfterInject() {
        initObserverableNotificaiton();
    }

    /**
     * Initialize the notification observable.
     */
    private void initObserverableNotificaiton() {
        notificationBroadcastManager.addObserver(this,
                NotificationBroadcastManager.Type.FRIEND_REQUEST,
                friendRequestReceiver);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            setFragment(new ConsultFragment_(), getString(R.string.sidebar_consult));
        } else {
            //super.onBackPressed();
            moveTaskToBack(true);

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        boolean disconnected = false;
        switch (item.getItemId()) {
            case R.id.sidebar_consult:
                fragment = new ConsultFragment_();
                break;
            case R.id.sidebar_create:
                fragment = new CreateFragment_();
                break;
            case R.id.sidebar_statistics:
                fragment = new StatisticFragment_();
                break;
            case R.id.sidebar_friends:
                fragment = new NetworkFragment_();
                break;
            case R.id.sidebar_options:
                disconnected = true;
                break;
            case R.id.sidebar_logout:
                disconnected = true;
                signOut();
                startActivity(new Intent(this, LoginActivity_.class));
                break;
            case R.id.sidebar_about:
                fragment = new AboutUsFragment_();
                break;
        }
        if (!disconnected) {
            assert fragment == null;
            setFragment(fragment, item.getTitle().toString());
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Change the content view with a specific fragment.
     *
     * @param fragment fragment to replace.
     * @param title    fragment title.
     */
    private void setFragment(Fragment fragment, String title) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();
        setTitle(title);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                status -> {
                    googleApiClient.disconnect();
                    //finish();
                });
    }

    private BroadcastReceiver friendRequestReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String uuid = intent.getStringExtra(NotificationBroadcastManager.EXTRA_FRIEND_REQUEST_RECEIVER);
            Realm realm = Realm.getDefaultInstance();
            Request request = realm.where(Request.class).equalTo("id", uuid).findFirst();
            if (request != null) {
                showFriendRequestAnswer(request);
            }
        }
    };

    void showFriendRequestAnswer(Request request) {
        Log.d(TAG, "Show Nickname Dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.notification_title_friend_request));
        builder.setMessage(request.getSender().getPseudo() + " vous demande en ami.");

        builder.setPositiveButton(R.string.notification_dialog_accept, (dialog, which) -> {

        });

        builder.setNegativeButton(R.string.notification_dialog_decline, (dialog, which) -> {
            Log.d(TAG, "Closing dialog...");
            dialog.cancel();
        });

        builder.show();
    }
}
