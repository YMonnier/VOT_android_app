package pm12016g3.tln.univ.fr.vot.utilities.views.list;

import android.content.Context;
import android.widget.LinearLayout;

import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.listview.basic.
 * File BaseItemView.java.
 * Created by Ysee on 23/05/2017 - 11:22.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public abstract class BaseItemView<T> extends LinearLayout
        implements Bindable<T> {
    public BaseItemView(Context context) {
        super(context);
    }
}
