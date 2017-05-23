package pm12016g3.tln.univ.fr.vot.utilities.views.list;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.listview.basic.
 * File BasicItem.java.
 * Created by Ysee on 23/05/2017 - 11:15.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class BasicItem {
    private String title;
    private boolean selected;

    public BasicItem(String title) {
        this.title = title;
        this.selected = false;
    }
}
