package pm12016g3.tln.univ.fr.vot.models;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.
 * File SocialChoice.java.
 * Created by Ysee on 18/05/2017 - 14:25.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class SocialChoice {
    public enum Type {
        SIMPLE_TRANSFARABLE_VOTE, MAJORITY_JUGMENT, SIMPLE, KEMENY_YOUNG
    }

    private String title;
    private String description;
    private Type type;

}
