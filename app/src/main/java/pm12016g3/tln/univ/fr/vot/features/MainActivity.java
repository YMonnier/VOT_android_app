package pm12016g3.tln.univ.fr.vot.features;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.network.NetworkFragment_;

@EActivity(R.layout.main_activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;



    @AfterViews
    void init() {
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.sidebar_consult:

                break;
            case R.id.sidebar_create:

                break;
            case R.id.sidebar_statistics:

                break;
            case R.id.sidebar_friends:
                fragment = new NetworkFragment_();
                break;
            case R.id.sidebar_options:

                break;
            case R.id.sidebar_logout:

                break;
            case R.id.sidebar_about:

                break;
        }
        assert fragment == null;
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();



        setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
