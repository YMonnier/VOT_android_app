package pm12016g3.tln.univ.fr.vot.utilities.stylesheet;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.stylesheet.
 * File Stylesheet.java.
 * Created by Ysee on 16/05/2017 - 10:27.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class Stylesheet {
    public enum Font {
        DEFAULT("sans");

        private final String text;

        /**
         * Font text.
         *
         * @param text, font text
         */
        private Font(final String text) {
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


    public static Typeface font(Context context, Font type) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/" + type.text + ".ttf");
    }
}
