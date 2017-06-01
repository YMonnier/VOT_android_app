package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import lombok.Data;

/**
 * Created by wenlixing on 22/05/2017.
 */
@Data
public class ParticipationItem {

    private String choice_title;
    private boolean checked;

    public ParticipationItem(String choice_title) {
        this.choice_title = choice_title;
        this.checked = true;
    }
}
