package pm12016g3.tln.univ.fr.vot.models.location;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.location.
 * File Geo.java.
 * Created by Ysee on 30/05/2017 - 11:42.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
public class Geo {
    private double latitude;
    private double longitude;

    private Geo(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public static class Builder {
        private double latitude;
        private double longitude;

        public Geo build() {
            return new Geo(this);
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }
    }
}
