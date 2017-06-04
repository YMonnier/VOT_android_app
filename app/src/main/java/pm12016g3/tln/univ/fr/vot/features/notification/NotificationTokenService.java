package pm12016g3.tln.univ.fr.vot.features.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.notification.
 * File NotificationTokenService.java.
 * Created by Ysee on 03/06/2017 - 13:34.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class NotificationTokenService extends FirebaseInstanceIdService {
    private static final String TAG = NotificationTokenService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + token);
    }
}