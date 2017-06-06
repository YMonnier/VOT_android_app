package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.
 * File SimpleVoteFragmentListAdapter.java.
 * Created by Ysee on 23/05/2017 - 15:19.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class SimpleVoteFragmentListAdapter
        extends ListViewAdapterBase<Candidat, SimpleVoteItemView> {
    @Override
    protected SimpleVoteItemView onCreateItemView(ViewGroup parent) {
        return SimpleVoteItemView_.build(parent.getContext());
    }
}
