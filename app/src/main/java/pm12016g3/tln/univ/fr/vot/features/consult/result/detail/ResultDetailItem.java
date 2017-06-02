package pm12016g3.tln.univ.fr.vot.features.consult.result.detail;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import lombok.Data;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by wenlixing on 02/06/2017.
 */
@Data
public class ResultDetailItem  {
    private String voter;
    private String choice;

    public ResultDetailItem(String voter, String choice) {
        this.voter = voter;
        this.choice = choice;
    }
}
