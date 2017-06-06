package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.features.network.research.custom.SendOnClickListener;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.research.
 * File NetworkResearchListAdapter.java.
 * Created by Ysee on 17/05/2017 - 15:33.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class NetworkResearchListAdapter
        extends ListViewAdapterBase<User, NetworkResearchItemView> {
    private final String TAG = NetworkResearchListAdapter.class.getSimpleName();

    /**
     * Custom Listener.
     */
    private SendOnClickListener<User> sendListener = null;

    @Override
    protected NetworkResearchItemView onCreateItemView(ViewGroup parent) {
        return NetworkResearchItemView_.build(parent.getContext());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NetworkResearchItemView v = (NetworkResearchItemView) super.getView(i, view, viewGroup);
        //v.invitation.setOnClickListener();
        //ImageButton invitation =
        //       ((ImageButton) v.findViewById(R.id.network_research_item_invitation));
        Log.d(TAG, "Get View...");
        v.invitation.setOnClickListener(view1 -> {
            if (sendListener != null) {
                sendListener.onClick(getItem(i));
            }
        });
        return v;
    }

    /**
     * Set the custom Send On Click listener.
     * @param listener custom listener.
     */
    public void setListener(SendOnClickListener<User> listener) {
        sendListener = listener;
    }
}
