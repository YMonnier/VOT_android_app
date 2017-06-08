package pm12016g3.tln.univ.fr.vot.models.shared;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.shared.
 * File SCSMajorityBallot.java.
 * Created by Ysee on 23/05/2017 - 10:20.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class SCSMajorityBallot implements SCData {
    @Expose
    private boolean ordered;

    @Expose
    @SerializedName(value = "choice_nb")
    private int nbChoice;

    public SCSMajorityBallot(boolean ordered, int nbChoice) {
        this.ordered = ordered;
        this.nbChoice = nbChoice;
    }
}
