package pm12016g3.tln.univ.fr.vot.features.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import org.androidannotations.annotations.EBean;

/**
 * Project pm12016g3_android_app.
 * Package pm12016g3.tln.univ.fr.vot.features.notification.
 * File NotificationBroadcastManager.java.
 * Created by Ysee on 07/06/2017 - 13:58.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class NotificationBroadcastManager {

    private static final String FRIEND_REQUEST_RECEIVER = "FRIEND_REQUEST_RECEIVER";
    public static final String EXTRA_FRIEND_REQUEST_RECEIVER = "EXTRA_FRIEND_REQUEST_RECEIVER";

    private static final String FRIEND_REQUEST_ANSWER_RECEIVER = "FRIEND_REQUEST_ANSWER_RECEIVER";
    public static final String EXTRA_FRIEND_REQUEST_ANSWER_RECEIVER = "EXTRA_FRIEND_REQUEST_ANSWER_RECEIVER";

    private static final String SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER = "SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER";
    public static final String EXTRA_SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER = "EXTRA_SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER";

    private static final String SOCIAL_CHOICE_CLOSED_RECEIVER = "SOCIAL_CHOICE_CLOSED_RECEIVER";
    public static final String EXTRA_SOCIAL_CHOICE_CLOSED_RECEIVER = "EXTRA_SOCIAL_CHOICE_CLOSED_RECEIVER";

    private static final String SOCIAL_CHOICE_INVITATION_RECEIVER = "SOCIAL_CHOICE_INVITATION_RECEIVER";
    public static final String EXTRA_SOCIAL_CHOICE_INVITATION_RECEIVER = "EXTRA_SOCIAL_CHOICE_INVITATION_RECEIVER";

    public enum Type {
        SOCIAL_CHOICE_INVITATION,
        FRIEND_REQUEST,
        FRIEND_REQUEST_ANSWER,
        SOCIAL_CHOICE_IMMINENT_CLOSE,
        SOCIAL_CHOICE_CLOSED
    }

    /**
     * Send message via LocalBroadcast.
     *
     * @param context context
     * @param type    recipient
     * @param json    JSON data
     */
    public void send(Context context, Type type, String json) {



        String filter = null;
        String extra = null;
        switch (type) {
            case SOCIAL_CHOICE_INVITATION:
                filter = SOCIAL_CHOICE_INVITATION_RECEIVER;
                extra = EXTRA_SOCIAL_CHOICE_INVITATION_RECEIVER;
                break;
            case FRIEND_REQUEST:
                filter = FRIEND_REQUEST_RECEIVER;
                extra = EXTRA_FRIEND_REQUEST_RECEIVER;
                break;
            case FRIEND_REQUEST_ANSWER:
                filter = FRIEND_REQUEST_ANSWER_RECEIVER;
                extra = EXTRA_FRIEND_REQUEST_ANSWER_RECEIVER;
                break;
            case SOCIAL_CHOICE_CLOSED:
                filter = SOCIAL_CHOICE_CLOSED_RECEIVER;
                extra = EXTRA_SOCIAL_CHOICE_CLOSED_RECEIVER;
                break;
            case SOCIAL_CHOICE_IMMINENT_CLOSE:
                filter = SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER;
                extra = EXTRA_SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER;
                break;
        }
        if (filter == null || extra == null)
            throw new AssertionError("Filter or extra should be not null");

        Intent intent = new Intent(filter);
        intent.putExtra(extra, json);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * Register a Receiver to a speicifc view.
     *
     * @param context  context
     * @param type     Observer Type
     * @param callback a BroadcastReceiver callback
     */
    public void addObserver(Context context, Type type, BroadcastReceiver callback) {
        String filter = null;
        switch (type) {
            case SOCIAL_CHOICE_INVITATION:
                filter = SOCIAL_CHOICE_INVITATION_RECEIVER;
                break;
            case FRIEND_REQUEST:
                filter = FRIEND_REQUEST_RECEIVER;
                break;
            case FRIEND_REQUEST_ANSWER:
                filter = FRIEND_REQUEST_ANSWER_RECEIVER;
                break;
            case SOCIAL_CHOICE_CLOSED:
                filter = SOCIAL_CHOICE_CLOSED_RECEIVER;
                break;
            case SOCIAL_CHOICE_IMMINENT_CLOSE:
                filter = SOCIAL_CHOICE_IMMINENT_CLOSE_RECEIVER;
                break;
        }
        if (filter == null)
            throw new AssertionError("Filter should be not null");

        LocalBroadcastManager
                .getInstance(context)
                .registerReceiver(callback,
                        new IntentFilter(filter));
    }
}
