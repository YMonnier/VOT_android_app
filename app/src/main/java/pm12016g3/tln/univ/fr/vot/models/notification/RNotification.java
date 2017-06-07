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
    public enum MessageType {
        SOCIAL_CHOICE_INVITATION(1),
        FRIEND_REQUEST(2),
        FRIEND_REQUEST_ACCEPTED(3),
        FRIEND_REQUEST_DECLINE(4),
        SOCIAL_CHOICE_IMMINENT_CLOSE(5),
        SOCIAL_CHOICE_CLOSED(6);

        public long id;
        private MessageType(long id) {
            this.id = id;
        }
    }
    @SerializedName("message_id")
    private Long id;
    private T content;
}
