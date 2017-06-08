package pm12016g3.tln.univ.fr.vot.models.notification.shared;

import com.google.gson.annotations.Expose;

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
    @Expose
    private Long id;
    @Expose
    private boolean confirm;
    @Expose
    private User receiver;
    @Expose
    private User sender;
}
