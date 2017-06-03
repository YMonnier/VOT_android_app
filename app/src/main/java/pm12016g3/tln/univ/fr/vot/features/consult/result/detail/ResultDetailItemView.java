package pm12016g3.tln.univ.fr.vot.features.consult.result.detail;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BaseItemView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicCheckItemAbstractView;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Created by wenlixing on 02/06/2017.
 */
@EViewGroup(R.layout.consult_result_detail_item)
public class ResultDetailItemView extends BaseItemView<ResultDetailItem> implements Bindable<ResultDetailItem> {

    @ViewById(R.id.result_details_voter)
    TextView voter;

    @ViewById(R.id.result_details_choice)
    TextView choice;

    public ResultDetailItemView(Context context) {
        super(context);
    }

    @Override
    public void bind(ResultDetailItem object) {
        voter.setText(object.getVoter());
        choice.setText(object.getChoice());
    }
}
