package pm12016g3.tln.univ.fr.vot.features.network;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BaseItemView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EViewGroup(R.layout.show_friend_invitation_item_view)
public class ShowFriendInvitationItemView
        extends BaseItemView<User> {
    @ViewById(R.id.friend_pseudo)
    TextView friendPseudo;

    @ViewById(R.id.friend_ok)
    ImageButton ok;

    @ViewById(R.id.friend_reject)
    ImageButton reject;

    public ShowFriendInvitationItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(User object) {
        friendPseudo.setText(object.getPseudo());
    }
}
