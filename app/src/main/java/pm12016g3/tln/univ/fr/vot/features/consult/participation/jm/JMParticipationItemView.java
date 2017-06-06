package pm12016g3.tln.univ.fr.vot.features.consult.participation.jm;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BaseItemView;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@EViewGroup(R.layout.jm_participation_item_view)
public class JMParticipationItemView extends BaseItemView<JMCandidat> {

    public JMParticipationItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.tv_name)
    TextView tv_name;

    @ViewById(R.id.s_labels)
    Spinner s_labels;

    @Override
    public void bind(JMCandidat object) {
        this.tv_name.setText(object.getName());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, object.getLabels()); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_labels.setAdapter(spinnerArrayAdapter);
    }

}
