package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.content.Context;

import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItemAbstractView;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.research.
 * File NetworkResearchItem.java.
 * Created by Ysee on 17/05/2017 - 15:17.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class NetworkResearchItemView
        extends BasicItemAbstractView<BasicItem> {
    public NetworkResearchItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(BasicItem object) {
        this.title.setText(object.getTitle());
        if (object.isSelected())
            selecting.setVisibility(VISIBLE);
        else
            selecting.setVisibility(INVISIBLE);
    }
}
