package pm12016g3.tln.univ.fr.vot.utilities.views.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.views.list.
 * File ListViewAdapeterBase.java.
 * Created by Ysee on 16/05/2017 - 11:34.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ListViewAdapterBase<T, V extends View & Bindable<T>> extends BaseAdapter {
    private final String TAG = ListViewAdapterBase.class.getSimpleName();

    /**
     * List of item.
     */
    private List<T> items = new ArrayList<>();

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        V newView;
        if (view == null) {
            newView = onCreateItemView(viewGroup);
        } else {
            newView = (V) view;
        }

        newView.bind(items.get(i));
        return newView;
    }

    /**
     * Create the Item view.
     * @param parent Parent view.
     * @return a View which is Bindable.
     */
    protected abstract V onCreateItemView(ViewGroup parent);

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Add an item to the current data set.
     * @param item item.
     * @return The current View Adapter
     */
    public ListViewAdapterBase<T, V> add(T item) {
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
