package pm12016g3.tln.univ.fr.vot.features.consult.result.detail;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 02/06/2017.
 */
@EBean
public class ResultDetailListAdapter extends ListViewAdapterBase<ResultDetailItem, ResultDetailItemView> {
    @Override
    protected ResultDetailItemView onCreateItemView(ViewGroup parent) {
        return ResultDetailItemView_.build(parent.getContext());
    }
}
