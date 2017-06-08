package pm12016g3.tln.univ.fr.vot.utilities.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.springframework.web.client.RestClientException;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.network.
 * File NetworkUtils.java.
 * Created by Ysee on 31/05/2017 - 14:49.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class NetworkUtils {
    /**
     * Allowing to know if the phone
     * is connected to a internet network.
     * @param context source activity
     * @return true id connected to the internet network, otherwise false.
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean is5xxError(RestClientException e) {
        return e.getLocalizedMessage().contains("500");
    }

    public static boolean is403Error(RestClientException e) {
        return e.getLocalizedMessage().contains("403");
    }
}
