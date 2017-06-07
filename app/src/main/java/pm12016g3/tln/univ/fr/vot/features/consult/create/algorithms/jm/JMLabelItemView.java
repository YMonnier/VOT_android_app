package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.jm;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.Label;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BaseItemView;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@EViewGroup(R.layout.jm_label_item_view)
class JMLabelItemView extends BaseItemView<Label> {
    @ViewById(R.id.tv_number)
    TextView tvNumber;

    @ViewById(R.id.et_label)
    EditText etLabel;

    public JMLabelItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(Label object) {
        tvNumber.setText(Integer.toString(object.getNumber()));
        etLabel.setText(object.getName());
    }



}
