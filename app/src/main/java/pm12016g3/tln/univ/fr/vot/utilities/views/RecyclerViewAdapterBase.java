package pm12016g3.tln.univ.fr.vot.utilities.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.views.
 * File RecyclerViewAdapterBase.java.
 * Created by Ysee on 15/05/2017 - 16:17.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items = new ArrayList<>();

    @Override
    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return items.size();
    }

}
