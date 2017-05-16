package pm12016g3.tln.univ.fr.vot.features.shared.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar.NavItem;
import pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar.SidebarListAdapter;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar.
 * File MainActivity.java.
 * Created by Ysee on 15/05/2017 - 14:15.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
@EActivity(R.layout.shared_sidebar_main_activity)
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.drawerPane)
    RelativeLayout drawerPane;

    @ViewById(R.id.navList)
    ListView listView;

    List<NavItem> items;

    @Bean
    SidebarListAdapter adapter;

    @AfterViews
    void init() {
        SidebarListAdapter sidebarListAdapter = new SidebarListAdapter();
        sidebarListAdapter
                .add(new NavItem(NavItem.Type.CONSULT))
                .add(new NavItem(NavItem.Type.CREATE))
                .add(new NavItem(NavItem.Type.STATISTICS))
                .add(new NavItem(NavItem.Type.NETWORK))
                .add(new NavItem(NavItem.Type.OPTIONS))
                .add(new NavItem(NavItem.Type.LOGOUT))
                .add(new NavItem(NavItem.Type.ABOUT));
        listView.setAdapter(sidebarListAdapter);
    }

    @ItemClick
    void navListItemClicked(NavItem navItem) {
        Log.d(TAG, "Sidebar item clicked: " + navItem);
        selectItemFromMenu(navItem.getType());
    }

    private void selectItemFromMenu(NavItem.Type type) {
        
    }
}
