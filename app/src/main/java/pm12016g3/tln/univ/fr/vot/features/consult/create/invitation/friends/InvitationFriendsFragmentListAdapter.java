package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.
 * File InvitationFriendsFragmentListAdapter.java.
 * Created by Ysee on 29/05/2017 - 10:25.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class InvitationFriendsFragmentListAdapter
        extends ListViewAdapterBase<BasicItem, InvitationFriendsFragmentItemView> {
    @Override
    protected InvitationFriendsFragmentItemView onCreateItemView(ViewGroup parent) {
        return InvitationFriendsFragmentItemView_.build(parent.getContext());
    }
}
