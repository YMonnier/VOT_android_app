package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Created by wenlixing on 22/05/2017.
 */
@EViewGroup(R.layout.consult_participation_participation_item)
public class ParticipationItemView extends LinearLayout implements Bindable<ParticipationItem> {

    @ViewById(R.id.participation_choice_id)
    TextView id;

    @ViewById(R.id.participation_choice_title)
    TextView choiceTitle;

    @ViewById(R.id.participation_checkbox)
    CheckBox checkBox;

    public ParticipationItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(ParticipationItem object) {
        id.setText(object.getId());
        choiceTitle.setText(object.getChoice());
        checkBox.setChecked(object.isChecked());
    }
}
