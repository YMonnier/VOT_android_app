package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 22/05/2017.
 */
@EBean
public class ParticipationListAdapter extends ListViewAdapterBase<ParticipationItem,ParticipationItemView> {
    @Override
    protected ParticipationItemView onCreateItemView(ViewGroup parent) {
        return ParticipationItemView_.build(parent.getContext());
    }
}
