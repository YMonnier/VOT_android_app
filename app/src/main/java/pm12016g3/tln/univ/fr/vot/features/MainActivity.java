package pm12016g3.tln.univ.fr.vot.features;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.about.AboutUsFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.consult.ConsultFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment_;
import pm12016g3.tln.univ.fr.vot.features.network.NetworkFragment_;
import pm12016g3.tln.univ.fr.vot.features.root.LoginActivity;
import pm12016g3.tln.univ.fr.vot.features.root.LoginActivity_;

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        boolean disconnected = false;
        switch (item.getItemId()) {
            case R.id.sidebar_consult:

                break;
            case R.id.sidebar_create:
                fragment = new CreateFragment_();
                break;
            case R.id.sidebar_statistics:

                break;
            case R.id.sidebar_friends:
                fragment = new NetworkFragment_();
                break;
            case R.id.sidebar_options:

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
        if(!disconnected){
            assert fragment == null;
            setFragment(fragment, item.getTitle().toString());}
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Change the content view with a specific fragment.
     * @param fragment fragment to replace.
     * @param title fragment title.
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
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        googleApiClient.disconnect();
                        //finish();
                    }
                });
    }
}
