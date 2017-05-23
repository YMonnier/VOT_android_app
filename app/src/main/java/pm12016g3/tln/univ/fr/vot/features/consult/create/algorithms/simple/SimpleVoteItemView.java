package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.
 * File SimpleVoteItemView.java.
 * Created by Ysee on 23/05/2017 - 15:20.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EViewGroup(R.layout.shared_listview_basic_item_view)
public class SimpleVoteItemView
        extends BasicCheckItemAbstractView<BasicItem> {

    public SimpleVoteItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(BasicItem object) {
        title.setText(object.getTitle());
    }
}
