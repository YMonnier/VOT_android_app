package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;

import lombok.Data;

/**
 * Created by wenlixing on 22/05/2017.
 */
@Data
public class ParticipationItem {
    private String id;
    private String choice;
    private boolean checked;

    public ParticipationItem(String id, String choice){
        this.id = id;
        this.choice = choice;
        this.checked = true;
    }
}
