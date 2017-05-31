package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.
 * File InvitationItemView.java.
 * Created by Ysee on 29/05/2017 - 10:25.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
@EViewGroup(R.layout.shared_listview_basic_item_view)
public class InvitationFriendsFragmentItemView
        extends BasicCheckItemAbstractView<BasicItem> {

    public InvitationFriendsFragmentItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(BasicItem object) {
        title.setText(object.getTitle());
        if (object.isSelected())
            selecting.setVisibility(VISIBLE);
        else
            selecting.setVisibility(INVISIBLE);
    }
}
