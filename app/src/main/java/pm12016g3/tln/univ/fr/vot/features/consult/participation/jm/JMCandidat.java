package pm12016g3.tln.univ.fr.vot.features.consult.participation.jm;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@Data
public class JMCandidat {
    private String name;
    private List<String> labels = new ArrayList<>();
}
