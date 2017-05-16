package pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.shared.main.sidebar.
 * File NavItem.java.
 * Created by Ysee on 15/05/2017 - 14:20.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class NavItem {
    /**
     * Item title.
     */
    private String title;

    public NavItem(String title) {
        this.title = title;
    }
}
