package pm12016g3.tln.univ.fr.vot.models.notification;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.notification.
 * File RNotification.java.
 * Created by Ysee on 06/06/2017 - 21:24.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class RNotification<T> {
    @SerializedName("message_id")
    private Long id;
    private T content;
}
