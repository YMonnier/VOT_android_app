package pm12016g3.tln.univ.fr.vot.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Created by damienlemenager on 06/06/2017.
 */

@Data
public class Vote {
    @SerializedName("id_social_choice")
    private Long socialChoiceID;

    private Map<String, Object> data = new HashMap<>();

    public Vote(Long socialChoiceID) {
        this.socialChoiceID = socialChoiceID;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }
}
