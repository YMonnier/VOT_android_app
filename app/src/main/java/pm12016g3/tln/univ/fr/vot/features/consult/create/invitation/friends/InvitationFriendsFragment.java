package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends;

import android.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.InvitationFragment;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends.
 * File InvitationFriendsFragment.java.
 * Created by Ysee on 29/05/2017 - 15:04.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_invitation_friends_fragment)
public class InvitationFriendsFragment extends Fragment {

    /**
     * Friend list.
     */
    @ViewById(R.id.listView)
    ListView listView;

    /**
     * Friend list adapter.
     */
    @Bean
    InvitationFriendsFragmentListAdapter adapter;

    /**
     * Parent view.
     */
    InvitationFragment parent;

    @AfterViews
    void init() {
        parent = (InvitationFragment) getParentFragment();

        adapter
                .add(new BasicItem("John"))
                .add(new BasicItem("Robert"))
                .add(new BasicItem("Stephane"))
                .add(new BasicItem("Paul"))
                .add(new BasicItem("Georges"));
        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.listView)
    void clickOnListView(BasicItem item) {
        item.setSelected(!item.isSelected());
        adapter.notifyDataSetChanged();
    }
}
