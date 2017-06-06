package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.research.
 * File NetworkResearchListAdapter.java.
 * Created by Ysee on 17/05/2017 - 15:33.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class NetworkResearchListAdapter
        extends ListViewAdapterBase<User, NetworkResearchItemView>
        implements View.OnClickListener {
    @Override
    protected NetworkResearchItemView onCreateItemView(ViewGroup parent) {
        return NetworkResearchItemView_.build(parent.getContext());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =super.getView(i, view, viewGroup);
        ImageButton invitation =
                ((ImageButton)v.findViewById(R.id.network_research_item_invitation));
        invitation.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"Click image button",Toast.LENGTH_LONG).show();
    }

}
