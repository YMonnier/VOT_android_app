package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EViewGroup(R.layout.shared_listview_basic_item_view)
public class NetworkResearchItemView
        extends BasicCheckItemAbstractView<User> {
    public NetworkResearchItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(User object) {
        this.title.setText(object.getPseudo());
        if (object.isSelected())
            selecting.setVisibility(VISIBLE);
        else
            selecting.setVisibility(INVISIBLE);
    }
}
