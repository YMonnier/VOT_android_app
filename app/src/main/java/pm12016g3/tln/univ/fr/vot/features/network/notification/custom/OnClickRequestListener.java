package pm12016g3.tln.univ.fr.vot.features.network.notification.custom;

import pm12016g3.tln.univ.fr.vot.models.realm.Request;

/**
 * Project pm12016g3_android_app.
 * Package pm12016g3.tln.univ.fr.vot.features.network.notification.custom.
 * File OnClickRequestListener.java.
 * Created by Ysee on 07/06/2017 - 22:39.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public interface OnClickRequestListener {
    void onConfirm(Request request);
    void onDecline(Request request);
}
