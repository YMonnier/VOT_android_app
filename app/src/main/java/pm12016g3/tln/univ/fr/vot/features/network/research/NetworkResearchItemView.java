package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.research.
 * File NetworkResearchItem.java.
 * Created by Ysee on 17/05/2017 - 15:17.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EViewGroup(R.layout.network_friend_research_list_item)
public class NetworkResearchItemView extends LinearLayout
        implements Bindable<NetworkResearchItem> {

    @ViewById(R.id.network_friend_research_list_title)
    TextView title;

    @ViewById(R.id.network_friend_research_list_selecting)
    ImageView selecting;

    public NetworkResearchItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(NetworkResearchItem object) {
        title.setText(object.getTitle());
        if (object.isSelected())
            selecting.setVisibility(VISIBLE);
        else
            selecting.setVisibility(INVISIBLE);
    }
}
