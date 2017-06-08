package pm12016g3.tln.univ.fr.vot.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.network.Requestable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.
 * File SocialChoice.java.
 * Created by Ysee on 18/05/2017 - 14:25.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class SocialChoice<T>
        implements Requestable, Serializable {
    public enum Type {
        STV("STV"),
        JM("JM"),
        SM("SM"),
        KY("KY");

        private String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private Long id;

    private String title;

    private String description;

    private Type type;

    private boolean confidentiality;

    private boolean closed;

    private List<User> voted;

    private List<User> participants;

    @SerializedName(value = "end_date")
    private String endDate;

    @SerializedName(value = "value")
    private List<Candidat> candidats;

    private T data;

    public SocialChoice() {
        participants = new ArrayList<>();
    }

    public SocialChoice(String title, String description, Type type, boolean confidentiality, boolean closed) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.confidentiality = confidentiality;
        this.closed = closed;
        participants = new ArrayList<>();
    }

    /**
     * Return the drawable image ID of the respective social choice
     *
     * @return drawable image ID
     */
    public int getDrawableImage() {
        int res = 0;
        switch (type) {
            case STV:
                res = R.drawable.type_one;
                break;
            case JM:
                res = R.drawable.type_two;
                break;
            case SM:
                res = R.drawable.type_three;
                break;
            case KY:
                res = R.drawable.type_four;
                break;
        }
        return res;
    }

    public List<Candidat> getCandidat() {
        return this.candidats;
    }
}
