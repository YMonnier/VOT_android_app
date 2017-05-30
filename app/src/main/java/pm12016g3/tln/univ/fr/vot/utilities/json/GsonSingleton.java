package pm12016g3.tln.univ.fr.vot.utilities.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.json
 * File GsonSingleton.java.
 * Created by Ysee on 30/05/2017 - 09:39.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class GsonSingleton {
    private static Gson gson;
    public final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Singleton pattern,
     *
     * @return gson instance.
     */
    public static Gson getInstance() {
        if (gson == null) {
            return new GsonBuilder()
                    .setDateFormat(DATE_FORMAT)
                    .create();
        }
        if (gson == null)
            throw new AssertionError("The gson instance should noy be null");
        return gson;
    }
}
