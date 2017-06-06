package pm12016g3.tln.univ.fr.vot.features.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTAuthAPI;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.notification.
 * File NotificationTokenService.java.
 * Created by Ysee on 03/06/2017 - 13:34.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EService
public class NotificationTokenService extends FirebaseInstanceIdService {
    private static final String TAG = NotificationTokenService.class.getSimpleName();

    /**
     * Rest service to get
     * information from server.
     */
    @RestService
    VOTAuthAPI apiService;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        if (Settings.currentUser != null) {
            updateDeviceToken(token);
        }
        Log.d(TAG, "FCM Token: " + token);
    }

    @Background
    void updateDeviceToken(final String token) {
        Log.i(TAG, "updateDeviceToken on server: " + token);
        Settings.currentUser.setDeviceToken(token);
        try {
            apiService.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<User>> response = apiService.deviceToken(Settings.currentUser);
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is4xxClientError()
                    || response.getStatusCode().is5xxServerError()) {
                updateDeviceToken(token);
            }
        } catch (RestClientException e) {
            Log.d(TAG, "Cannot Update the device token: " + token);
        }
    }
}