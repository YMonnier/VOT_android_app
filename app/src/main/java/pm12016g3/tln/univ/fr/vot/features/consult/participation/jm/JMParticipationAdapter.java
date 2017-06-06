package pm12016g3.tln.univ.fr.vot.features.consult.participation.jm;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchItemView;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchItemView_;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchListAdapter;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@EBean
public class JMParticipationAdapter extends ListViewAdapterBase<JMCandidat, JMParticipationItemView> {

    private final String TAG = JMParticipationAdapter.class.getSimpleName();

    @Override
    protected JMParticipationItemView onCreateItemView(ViewGroup parent) {
        return JMParticipationItemView_.build(parent.getContext());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        JMParticipationItemView v = (JMParticipationItemView) super.getView(i, view, viewGroup);
        //v.invitation.setOnClickListener();
        //ImageButton invitation =
        //       ((ImageButton) v.findViewById(R.id.network_research_item_invitation));
        Log.d(TAG, "Get View...");

        return v;
    }
}
