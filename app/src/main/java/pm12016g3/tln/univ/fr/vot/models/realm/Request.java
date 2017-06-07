package pm12016g3.tln.univ.fr.vot.models.realm;

import java.util.UUID;

import io.realm.RealmObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pm12016g3.tln.univ.fr.vot.models.User;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.realm.
 * File Request.java.
 * Created by Ysee on 06/06/2017 - 22:04.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Request extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private User sender;
    private User receiver;

    public Request() {}
}
