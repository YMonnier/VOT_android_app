package pm12016g3.tln.univ.fr.vot.models;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@Data
public class Label {
    @Expose
    private int number;
    @Expose
    private String name;
}
