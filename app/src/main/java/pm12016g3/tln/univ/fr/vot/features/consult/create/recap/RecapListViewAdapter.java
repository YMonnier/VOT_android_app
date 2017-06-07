package pm12016g3.tln.univ.fr.vot.features.consult.create.recap;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.features.network.NetworkFragmentItemView;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 07/06/2017.
 */

@EBean
public class RecapListViewAdapter
        extends ListViewAdapterBase<BasicItem, RecapItemView> {
    @Override
    protected RecapItemView onCreateItemView(ViewGroup parent) {
        return RecapItemView_.build(parent.getContext());
    }
}
