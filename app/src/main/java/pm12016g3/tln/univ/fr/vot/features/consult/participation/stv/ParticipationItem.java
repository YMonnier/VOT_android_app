package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.woxthebox.draglistview.DragItem;

import lombok.Data;
import pm12016g3.tln.univ.fr.vot.R;

/**
 * Created by wenlixing on 22/05/2017.
 */
@Data
public class ParticipationItem {

    private int id;
    private String choice_title;
    private boolean checked;
    private static int count = 1;

    public ParticipationItem(String choice_title) {
        this.id = count;
        this.choice_title = choice_title;
        this.checked = true;
        count++;
    }
}
