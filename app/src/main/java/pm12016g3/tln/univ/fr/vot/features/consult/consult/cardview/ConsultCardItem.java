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
            case STV:
                res = R.drawable.type_one;
                break;
            case JM:
                res = R.drawable.type_two;
                break;
            case SM:
                res = R.drawable.type_three;
                break;
            case KY:
                res = R.drawable.type_four;
                break;
        }
        return res;
    }
}
