package pm12016g3.tln.univ.fr.vot.models.realm;

import com.google.gson.annotations.Expose;

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
    @Expose
    private Long id;

    @Expose
    private boolean confirm;

    @Expose
    private User sender;
    @Expose
    private User receiver;

    public Request() {}
}
