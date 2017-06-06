package pm12016g3.tln.univ.fr.vot.models;

import java.util.List;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.
 * File FriendRequest.java.
 * Created by Ysee on 06/06/2017 - 18:07.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class FriendRequest {
    List<Long> friends;

    public void add(Long id) {
        if (id != null)
            friends.add(id);
    }
}
