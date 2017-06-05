package pm12016g3.tln.univ.fr.vot.features.network;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 17/05/2017.
 */
@EBean
public class NetworkFragmentListAdapter extends ListViewAdapterBase<User, NetworkFragmentItemView> {
    @Override
    protected NetworkFragmentItemView onCreateItemView(ViewGroup parent) {
        return NetworkFragmentItemView_.build(parent.getContext());
    }
}
