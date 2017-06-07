package pm12016g3.tln.univ.fr.vot.features.consult.create.recap;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Created by wenlixing on 07/06/2017.
 */

@EViewGroup(R.layout.shared_listview_basic_item_view)
public class RecapItemView extends BasicCheckItemAbstractView<BasicItem> {

    public RecapItemView(Context context) {
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
