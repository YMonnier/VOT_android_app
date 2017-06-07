package pm12016g3.tln.univ.fr.vot.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pm12016g3.tln.univ.fr.vot.models.network.Requestable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.
 * File User.java.
 * Created by Ysee on 31/05/2017 - 14:14.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends RealmObject implements Requestable {
    @PrimaryKey
    private Long id;

    private String email;

    private String pseudo;

    private String picture;

    private double longitude;

    private double latitude;

    @SerializedName(value = "access_token")
    private String accessToken;

    @SerializedName(value = "device_token")
    private String deviceToken;

    private boolean selected;

    public User() {}
    private User(Builder builder) {
        this.email = builder.email;
        this.pseudo = builder.pseudo;
        this.picture = builder.picture;
        this.accessToken = builder.accessToken;
        this.deviceToken = builder.deviceToken;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    public static class Builder {
        private String email;
        private String pseudo;
        private String picture;
        private String accessToken;
        private String deviceToken;
        private double longitude;
        private double latitude;

        public User build() {
            return new User(this);
        }

        public Builder setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setPseudo(String pseudo) {
            this.pseudo = pseudo;
            return this;
        }

        public Builder setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
    }
}
