package pm12016g3.tln.univ.fr.vot.features.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.androidannotations.annotations.EService;

import io.realm.Realm;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.notification.shared.RNotifFriendRequest;
import pm12016g3.tln.univ.fr.vot.models.realm.Request;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.notification.
 * File NotificationReceiverService.java.
 * Created by Ysee on 03/06/2017 - 13:35.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EService
public class NotificationReceiverService extends FirebaseMessagingService {
    private static final String TAG = NotificationReceiverService.class.getSimpleName();
    public long id = 0;
    /**
     * RNotification Manager.
     */
    NotificationManager notificationManager;

    /**
     * Gson Generic Deserialiser;
     */
    GsonDeserializer gsonDeserializer;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        gsonDeserializer = new GsonDeserializer();
    }

    /**
     * Receive a notification which has been
     * pushed by the server to broadcast a social choice information/message.
     *
     * @param remoteMessage message pushed
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM RNotification Message: " + remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());

        if (remoteMessage.getData() != null) {
            if (remoteMessage.getData().containsKey(JsonKeys.MESSAGE_ID)) {
                Gson gson = GsonSingleton.getInstance();
                JsonObject json = gson
                        .fromJson(gson.toJson(remoteMessage.getData()), JsonObject.class);
                Log.d(TAG, json.toString());
                long messageID = json.get(JsonKeys.MESSAGE_ID).getAsLong();
                JsonObject content = gson.fromJson(json.get(JsonKeys.CONTENT).getAsString(), JsonObject.class);

                if (messageID == 1) {

                } else if (messageID == 2) {
                    treatsFriendRequest(content);
                } else if (messageID == 3) {

                } else if (messageID == 4) {

                } else if (messageID == 5) {

                }
            }
        }
    }

    /**
     * Treat the message received by notification
     *
     * @param value
     */
    private void treatsFriendRequest(JsonObject value) {
        Gson gson = GsonSingleton.getInstance();
        RNotifFriendRequest nfr = gson.fromJson(value, RNotifFriendRequest.class);
        String name = nfr.getRelation().getSender().getPseudo();

        push(getString(R.string.notification_title_friend_request),
                name + " " + getString(R.string.notification_friend_request));

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        final String USER_SENDER = "sender.pseudo";
        final String USER_RECEIVER = "receiver.pseudo";
        Request reqFromDB = realm.where(Request.class)
                .equalTo(USER_SENDER, nfr.getRelation().getSender().getPseudo())
                .equalTo(USER_RECEIVER, nfr.getRelation().getReceiver().getPseudo())
                .findFirst();
        if (reqFromDB == null) {
            realm.executeTransaction(realm1 -> {
                Request req = realm1.createObject(Request.class);
                req.setSender(nfr.getRelation().getSender());
                req.setReceiver(nfr.getRelation().getReceiver());
            });
        }
        showFriendRequestAnswer(nfr);
    }

    // TODO: Show Dialog
    void showFriendRequestAnswer(RNotifFriendRequest friendRequest) {
        Log.d(TAG, "Show Nickname Dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.notification_title_friend_request));
        builder.setMessage(friendRequest.getRelation().getSender().getPseudo() + " vous demande en ami.");


        builder.setPositiveButton(R.string.notification_dialog_accept, (dialog, which) -> {

            /*Log.d(TAG, nickname);

            user.setPseudo(nickname);

            Uri picture = googleSignInAccount.getPhotoUrl();
            if (picture != null) {
                user.setPicture(picture.getPath());
            }*/


        });

        builder.setNegativeButton(R.string.notification_dialog_decline, (dialog, which) -> {
            Log.d(TAG, "Closing dialog...");
            dialog.cancel();
        });

        builder.show();
    }

    /**
     * Displays RNotification depending on the message received.
     *
     * @param title notification title.
     * @param body  notification content.
     */
    private void push(String title, String body) {
        id += 1;
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.type_two)
                .build();
        this.notificationManager.notify(1, notification);
    }
}
