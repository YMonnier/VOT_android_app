package pm12016g3.tln.univ.fr.vot.features.network.research;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.research.
 * File NetworkResearchItem.java.
 * Created by Ysee on 17/05/2017 - 15:17.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class NetworkResearchItem {

    private String title;
    private boolean selected;

    public NetworkResearchItem(String title) {
        this.title = title;
        this.selected = false;
    }
}
