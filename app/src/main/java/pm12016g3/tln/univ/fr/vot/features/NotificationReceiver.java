package pm12016g3.tln.univ.fr.vot.features;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import io.realm.Realm;
import lombok.Getter;
import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.notification.NotificationBroadcastManager;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.realm.Request;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;

/**
 * Project pm12016g3_android_app.
 * Package pm12016g3.tln.univ.fr.vot.features.test.
 * File NotificationReceiver.java.
 * Created by Ysee on 08/06/2017 - 06:16.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class NotificationReceiver {
    private static final String TAG = NotificationReceiver.class.getSimpleName();
    @RootContext
    MainActivity context;

    /**
     * Broadcast Manager
     */
    @Bean
    @Getter
    NotificationBroadcastManager notificationBroadcastManager;

    @RestService
    VOTFriendsAPI serviceAPI;

    /**
     * RNotification Manager.
     */
    @SystemService
    NotificationManager notificationManager;

    private Gson gson = GsonSingleton.getInstance();

    public void init() {
        notificationBroadcastManager.addObserver(context,
                NotificationBroadcastManager.Type.FRIEND_REQUEST,
                friendRequestReceiver);

        notificationBroadcastManager.addObserver(context,
                NotificationBroadcastManager.Type.SOCIAL_CHOICE_IMMINENT_CLOSE,
                closingSocialChoice);

        notificationBroadcastManager.addObserver(context,
                NotificationBroadcastManager.Type.SOCIAL_CHOICE_CLOSED,
                socialChoiceClosed);
    }

    private BroadcastReceiver closingSocialChoice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String json = intent.getStringExtra(NotificationBroadcastManager.EXTRA_SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER);
            final JsonObject content = gson.fromJson(json, JsonObject.class);
            final SocialChoice socialChoice = gson.fromJson(content.get(JsonKeys.SOCIAL_CHOICE), SocialChoice.class);
            final String title = socialChoice.getTitle();
            push("Choix Social " + title, "Hey! Vous n'avez pas voté !!");
        }
    };

    private BroadcastReceiver socialChoiceClosed = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String json = intent.getStringExtra(NotificationBroadcastManager.EXTRA_SOCIAL_CHOICE_CLOSED_RECEIVER);
            Log.d(TAG, "socialChoiceClosed BROADCAST");
            Log.d(TAG, json);
            final JsonObject content = gson.fromJson(json, JsonObject.class);
            Log.d(TAG, content.toString());
            final SocialChoice socialChoice = gson.fromJson(content.get(JsonKeys.SOCIAL_CHOICE), SocialChoice.class);
            Log.d(TAG, socialChoice.toString());
            final String title = socialChoice.getTitle();
            push("Choix Social", "Choix Social " + title + " est clos");
        }
    };

    private BroadcastReceiver friendRequestReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long defaultValueID = -122;

            Long id = Long.valueOf(intent.getStringExtra(NotificationBroadcastManager.EXTRA_FRIEND_REQUEST_RECEIVER));
            if (id != defaultValueID) {
                Realm realm = Realm.getDefaultInstance();
                Request request = realm.where(Request.class).equalTo("id", id).findFirst();
                if (request != null) {
                    showFriendRequestAnswer(realm, request);
                }
            }
        }
    };

    /**
     * Show a dialog which ask the user to confirm or decline
     * the Friend Request received by Notification (FCM)
     *
     * @param request Request received by Notification.
     * @param realm   Realm Instance Database
     */
    void showFriendRequestAnswer(Realm realm, Request request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.notification_title_friend_request));
        builder.setMessage(request.getSender().getPseudo() + " vous demande en ami.");

        builder.setPositiveButton(R.string.notification_dialog_accept, (dialog, which) -> {
            realm.beginTransaction();
            request.setConfirm(true);
            realm.commitTransaction();
            sendFriendRequestAnswer(request.getId(), request.isConfirm());
            dialog.cancel();
        });

        builder.setNegativeButton(R.string.notification_dialog_decline, (dialog, which) -> {
            realm.beginTransaction();
            request.setConfirm(false);
            realm.commitTransaction();
            sendFriendRequestAnswer(request.getId(), request.isConfirm());
            dialog.cancel();
        });

        builder.show();
    }

    @Background
    void sendFriendRequestAnswer(Long id, boolean answer) {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<JsonObject> response = serviceAPI.answer(id, answer);
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                Realm realm = Realm.getDefaultInstance();
                Request req = realm.where(Request.class).equalTo("id", id).findFirst();
                if (req != null) {
                    realm.beginTransaction();
                    req.deleteFromRealm();
                    realm.commitTransaction();
                }
            } else {
                sendFriendRequestAnswer(id, answer);
            }
        } catch (RestClientException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    /**
     * Displays RNotification depending on the message received.
     *
     * @param title notification title.
     * @param body  notification content.
     */
    private void push(String title, String body) {
        Settings.notificationID += 1;
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.type_two)
                .build();
        this.notificationManager.notify(Settings.notificationID, notification);
    }
}
