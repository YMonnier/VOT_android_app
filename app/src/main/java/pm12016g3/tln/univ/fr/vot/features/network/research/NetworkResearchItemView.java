package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BaseItemView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by wenlixing on 17/05/2017.
 */

@EViewGroup(R.layout.network_network_research_item_view)
public class NetworkResearchItemView
        extends BaseItemView<User> {
    public NetworkResearchItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.network_research_item_text)
    TextView title;

    @ViewById(R.id.network_research_item_invitation)
    ImageButton invitation;

    @Override
    public void bind(User object) {
        this.title.setText(object.getPseudo());
    }
}
