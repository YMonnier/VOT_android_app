package pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.stylesheet.Stylesheet;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar.
 * File NavItemView.java.
 * Created by Ysee on 15/05/2017 - 16:30.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EViewGroup(R.layout.shared_sidebar_nav_item_view)
public class NavItemView extends RelativeLayout {
    private final String TAG = NavItemView.class.getSimpleName();

    @ViewById
    protected TextView title;

    public NavItemView(Context context) {
        super(context);
        title.setTypeface(Stylesheet.font(this.getContext(), Stylesheet.Font.DEFAULT));
    }


    public void bind(NavItem navItem) {
        Log.d(TAG, "Bind: " + navItem);
        title.setText(navItem.getTitle());

        Log.d(TAG, "Bind: DONE");
    }
}
