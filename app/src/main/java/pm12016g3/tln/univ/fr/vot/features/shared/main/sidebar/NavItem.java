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
    public enum Type {
        CONSULT("Consulter"),
        CREATE("Créer"),
        STATISTICS("Statistiques"),
        NETWORK("Amis"),
        OPTIONS("Options"),
        ABOUT("À propos"),
        LOGOUT("Déconnexion");

        private final String text;

        /**
         * Font text.
         *
         * @param text, font text
         */
        private Type(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * Item title.
     */
    private Type type;

    public NavItem(Type type) {
        this.type = type;
    }
}
