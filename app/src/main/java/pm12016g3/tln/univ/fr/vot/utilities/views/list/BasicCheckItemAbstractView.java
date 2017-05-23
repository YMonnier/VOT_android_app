package pm12016g3.tln.univ.fr.vot.utilities.views.list;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;


/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.listview.basic.
 * File BasicItemAbstractView.java.
 * Created by Ysee on 23/05/2017 - 11:15.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
@EViewGroup
public abstract class BasicCheckItemAbstractView<T> extends BaseItemView<T> {
    @ViewById(R.id.shared_list_basic_text_item)
    protected TextView title;

    @ViewById(R.id.shared_list_basic_selection_item)
    protected ImageView selecting;

    public BasicCheckItemAbstractView(Context context) {
        super(context);
    }
}
