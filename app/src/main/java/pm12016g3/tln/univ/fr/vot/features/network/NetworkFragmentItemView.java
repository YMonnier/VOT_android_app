package pm12016g3.tln.univ.fr.vot.features.network;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EViewGroup(R.layout.shared_listview_basic_item_view)
public class NetworkFragmentItemView
        extends BasicCheckItemAbstractView<BasicItem> {

    public NetworkFragmentItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(BasicItem object) {
        title.setText(object.getTitle());
        if (object.isSelected())
            selecting.setVisibility(VISIBLE);
        else
            selecting.setVisibility(INVISIBLE);
    }
}
