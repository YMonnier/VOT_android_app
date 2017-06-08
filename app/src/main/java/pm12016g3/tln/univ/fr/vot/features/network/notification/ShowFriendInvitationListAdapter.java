package pm12016g3.tln.univ.fr.vot.features.network.notification;

import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.features.network.notification.custom.OnClickRequestListener;
import pm12016g3.tln.univ.fr.vot.models.realm.Request;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 17/05/2017.
 */
@EBean
public class ShowFriendInvitationListAdapter
        extends ListViewAdapterBase<Request, ShowFriendInvitationItemView>
        implements View.OnClickListener {

    private OnClickRequestListener listener;

    @Override
    protected ShowFriendInvitationItemView onCreateItemView(ViewGroup parent) {
        return ShowFriendInvitationItemView_.build(parent.getContext());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShowFriendInvitationItemView v = (ShowFriendInvitationItemView) super.getView(i, view, viewGroup);

        v.ok.setOnClickListener(view1 -> {
            if (listener != null) {
                listener.onConfirm(getItem(i));
            }
        });

        v.reject.setOnClickListener(view1 -> {
            if (listener != null) {
                listener.onDecline(getItem(i));
            }
        });
        return v;
    }

    @Override
    public void onClick(View view) {
        System.out.println(view);
    }

    /**
     * Set the custom Send On Click listener.
     *
     * @param listener custom listener.
     */
    public void setListener(OnClickRequestListener listener) {
        this.listener = listener;
    }
}
