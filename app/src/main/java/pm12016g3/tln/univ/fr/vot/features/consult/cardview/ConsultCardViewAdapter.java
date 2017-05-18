package pm12016g3.tln.univ.fr.vot.features.consult.cardview;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.utilities.views.recycler.RecyclerViewAdapterBase;
import pm12016g3.tln.univ.fr.vot.utilities.views.recycler.ViewWrapper;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.cardview.
 * File ConsultCardViewAdapter.java.
 * Created by Ysee on 18/05/2017 - 11:44.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class ConsultCardViewAdapter extends RecyclerViewAdapterBase<ConsultCardItem, ConsultCardView> {
    @Override
    protected ConsultCardView onCreateItemView(ViewGroup parent, int viewType) {
        return ConsultCardView_.build(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ConsultCardView> holder, int position) {
        ConsultCardView view = holder.getView();
        ConsultCardItem item = getItems().get(position);
        view.bind(item);
    }
}
