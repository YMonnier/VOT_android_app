package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
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
public class NetworkResearchListAdapter extends ListViewAdapterBase<BasicItem, NetworkResearchItemView> {
    @Override
    protected NetworkResearchItemView onCreateItemView(ViewGroup parent) {
        return NetworkResearchItemView_.build(parent.getContext());
    }
}
