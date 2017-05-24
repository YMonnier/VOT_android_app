package pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview;

import lombok.Data;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview.
 * File ConsultCardItem.java.
 * Created by Ysee on 18/05/2017 - 11:29.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class ConsultCardItem {
    private SocialChoice.Type type;
    private String title;
    private boolean closed;

    public ConsultCardItem(SocialChoice.Type type, String title, boolean closed ) {
        this.type = type;
        this.title = title;
        this.closed = closed;
    }

    public int getDrawableImage() {
        int res = 0;
        switch (type) {
            case SIMPLE_TRANSFARABLE_VOTE:
                res = R.drawable.type_one;
                break;
            case MAJORITY_JUGMENT:
                res = R.drawable.type_two;
                break;
            case MAJORITY_BALLOT:
                res = R.drawable.type_three;
                break;
            case KEMENY_YOUNG:
                res = R.drawable.type_four;
                break;
        }
        return res;
    }
}
