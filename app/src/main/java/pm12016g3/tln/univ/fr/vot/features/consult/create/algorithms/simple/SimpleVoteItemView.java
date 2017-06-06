package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.content.Context;

import org.androidannotations.annotations.EViewGroup;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;

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
        extends BasicCheckItemAbstractView<Candidat> {

    public SimpleVoteItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(Candidat object) {
        this.title.setText(object.getName());
        if (object.isSelected())
            selecting.setVisibility(VISIBLE);
        else
            selecting.setVisibility(INVISIBLE);
    }
}
