package pm12016g3.tln.univ.fr.vot.models.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
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
    @PrimaryKey
    private Long id;
    private boolean confirm;
    private User sender;
    private User receiver;

    public Request() {}
}
