package pm12016g3.tln.univ.fr.vot.models.shared;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.shared.
 * File SCSimpleTransfarableVote.java.
 * Created by Ysee on 23/05/2017 - 10:21.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
@Data
public class SCSimpleTransfarableVote implements SCData {
    public enum Elimination {
        LESS("less"),
        OCCURENCE("occurrence");
        private String value;

        Elimination(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private int nbWinner;
    private Elimination elimination;

    public SCSimpleTransfarableVote(int nbWinner, Elimination elimination) {
        this.nbWinner = nbWinner;
        this.elimination = elimination;
    }
}
