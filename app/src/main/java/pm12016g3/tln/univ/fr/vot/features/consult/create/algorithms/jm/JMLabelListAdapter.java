package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.jm;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.models.Label;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@EBean
class JMLabelListAdapter extends ListViewAdapterBase<Label, JMLabelItemView> {

    @Override
    protected JMLabelItemView onCreateItemView(ViewGroup parent) {
        return JMLabelItemView_.build(parent.getContext());
    }

}
