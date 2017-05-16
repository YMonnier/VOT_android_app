package pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pm12016g3.tln.univ.fr.vot.utilities.views.RecyclerViewAdapterBase;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewWrapper;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.
 * File SidebarListAdapter.java.
 * Created by Ysee on 15/05/2017 - 10:30.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class SidebarListAdapter extends RecyclerViewAdapterBase<NavItem, NavItemView> {

    @RootContext
    Context context;

    @Override
    protected NavItemView onCreateItemView(ViewGroup parent, int viewType) {
        return NavItemView_.build(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewWrapper<NavItemView> holder, int position) {
        NavItemView view = holder.getView();
        view.bind(items.get(position));
    }
}
