package pm12016g3.tln.univ.fr.vot.models.notification.shared;

import lombok.Data;
import pm12016g3.tln.univ.fr.vot.models.User;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.notification.shared.
 * File RNotifRelation.java.
 * Created by Ysee on 06/06/2017 - 23:36.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class RNotifRelation {
    private Long id;
    private boolean confirm;
    private User receiver;
    private User sender;
}
