package pm12016g3.tln.univ.fr.vot.models.shared;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.shared.
 * File SCMajorityJudgment.java.
 * Created by Ysee on 23/05/2017 - 10:21.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class SCMajorityJudgment implements SCData {
    private List<String> labels = new ArrayList<>();

    public SCMajorityJudgment add(String label) {
        labels.add(label);
        return this;
    }
}
