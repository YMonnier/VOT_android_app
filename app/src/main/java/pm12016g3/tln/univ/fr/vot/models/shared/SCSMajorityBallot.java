package pm12016g3.tln.univ.fr.vot.models.shared;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> labels = new ArrayList<>();

    public SCSMajorityBallot add(String label) {
        labels.add(label);
        return this;
    }
}
