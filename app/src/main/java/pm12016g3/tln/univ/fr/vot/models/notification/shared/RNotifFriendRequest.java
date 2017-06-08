package pm12016g3.tln.univ.fr.vot.models.notification.shared;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.notification.shared.
 * File RNotifFriendRequest.java.
 * Created by Ysee on 06/06/2017 - 21:26.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class RNotifFriendRequest {
    @Expose
    private RNotifRelation relation;
}
