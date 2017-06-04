package pm12016g3.tln.univ.fr.vot.features.notification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.notification.
 * File NotificationReceiverService.java.
 * Created by Ysee on 03/06/2017 - 13:35.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class NotificationReceiverService extends FirebaseMessagingService {
    private static final String TAG = NotificationReceiverService.class.getSimpleName();
    /**
     * Receive a notification which has been
     * pushed by the server to broadcast a social choice information/message.
     *
     * @param remoteMessage message pushed
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " + remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());
    }
}
