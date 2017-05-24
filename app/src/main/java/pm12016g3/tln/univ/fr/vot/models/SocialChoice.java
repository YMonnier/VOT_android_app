package pm12016g3.tln.univ.fr.vot.models;

import lombok.Data;
import pm12016g3.tln.univ.fr.vot.models.shared.SCData;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.
 * File SocialChoice.java.
 * Created by Ysee on 18/05/2017 - 14:25.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class SocialChoice<T extends SCData> {
    public enum Type {
        SIMPLE_TRANSFARABLE_VOTE("STV"),
        MAJORITY_JUGMENT("JM"),
        MAJORITY_BALLOT("SM"),
        KEMENY_YOUNG("KY");

        private String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private String title;
    private String description;
    private Type type;
    private boolean confidentiality;
    private T data;

    public SocialChoice() {
    }

    public SocialChoice(String title, String description, Type type, boolean confidentiality) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.confidentiality = confidentiality;
    }
}
