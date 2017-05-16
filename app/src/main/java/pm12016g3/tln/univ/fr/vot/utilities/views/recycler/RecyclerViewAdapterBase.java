package pm12016g3.tln.univ.fr.vot.utilities.views.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

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
public abstract class RecyclerViewAdapterBase<T, V extends View & Bindable<T>> extends RecyclerView.Adapter<ViewWrapper<V>> {

    /**
     * List of item.
     */
    private List<T> items = new ArrayList<>();

    @Override
    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }
    /**
     * Create the Item view.
     * @param parent Parent view.
     * @return a View which is Bindable.
     */
    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Add an item to the current data set.
     * @param item item.
     * @return The current View Adapter
     */
    public RecyclerViewAdapterBase<T, V> add(T item) {
        items.add(item);
        notifyDataSetChanged();
        return this;
    }

    /**
     * Add an collection of item to the current data set.
     * @param collection collection of item.
     */
    public void addAll(Collection<T> collection) {
        items.addAll(collection);
        notifyDataSetChanged();
    }

    /**
     * Clear the data set.
     */
    public void clear() {
        items.clear();
    }
}
