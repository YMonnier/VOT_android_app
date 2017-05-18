package pm12016g3.tln.univ.fr.vot.features.network;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Created by wenlixing on 17/05/2017.
 */
@EViewGroup(R.layout.network_friend_list_item)
public class NetworkFragmentItemView extends LinearLayout
        implements Bindable<NetWorkFragmentItem> {

    @ViewById(R.id.network_friend_list_title)
    TextView title;

    public NetworkFragmentItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(NetWorkFragmentItem object) {
        title.setText(object.getTitle());
    }
}
