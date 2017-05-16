package pm12016g3.tln.univ.fr.vot.features.shared.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
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
    @ViewById(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.drawerPane)
    RelativeLayout drawerPane;

    @ViewById(R.id.navList)
    RecyclerView listView;

    List<NavItem> items;

    @Bean
    SidebarListAdapter adapter;

    @AfterViews
    void init() {
        items = new ArrayList<>();
        items.add(new NavItem("Consulter"));
        items.add(new NavItem("Créer"));
        items.add(new NavItem("Statistiques"));
        items.add(new NavItem("Amis"));
        items.add(new NavItem("Options"));
        items.add(new NavItem("Déconnexion"));
        items.add(new NavItem("À propos"));

        SidebarListAdapter sidebarListAdapter = new SidebarListAdapter();
        sidebarListAdapter.setItems(items);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);
        listView.setAdapter(sidebarListAdapter);
    }
}
