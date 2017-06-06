package pm12016g3.tln.univ.fr.vot.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@Data
public class JMVote {
    @SerializedName("id_social_choice")
    private Long socialChoiceID;
    private Map<String, Object> data = new HashMap<>();

    public JMVote(Long socialChoiceID) {
        this.socialChoiceID = socialChoiceID;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }
}

