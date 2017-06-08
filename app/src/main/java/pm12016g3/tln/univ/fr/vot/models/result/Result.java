package pm12016g3.tln.univ.fr.vot.models.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

import lombok.Data;

/**
 * Project pm12016g3_android_app.
 * Package pm12016g3.tln.univ.fr.vot.models.result.
 * File Result.java.
 * Created by Ysee on 08/06/2017 - 04:17.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class Result {
    @Expose
    @SerializedName("resultats")
    private Map<String, String> value;

    @Expose
    @SerializedName("stats")
    private Map<String, String> statistics;
}
