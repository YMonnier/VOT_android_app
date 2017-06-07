package pm12016g3.tln.univ.fr.vot.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.
 * File Candidat.java.
 * Created by Ysee on 06/06/2017 - 12:28.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class Candidat {
    private Long id;
    private String name;
    private boolean selected;
    private List<String> labels = new ArrayList<>();
    private String labelSelected = "";

    public Candidat(String name) {
        this.name = name;
    }
}
