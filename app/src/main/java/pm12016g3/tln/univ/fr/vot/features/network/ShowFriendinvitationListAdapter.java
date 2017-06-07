package pm12016g3.tln.univ.fr.vot.features.network;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 17/05/2017.
 */
@EBean
public class ShowFriendinvitationListAdapter
        extends ListViewAdapterBase<User, ShowFriendInvitationItemView> {

    @Override
    protected ShowFriendInvitationItemView onCreateItemView(ViewGroup parent) {
        return ShowFriendInvitationItemView_.build(parent.getContext());
    }
}
