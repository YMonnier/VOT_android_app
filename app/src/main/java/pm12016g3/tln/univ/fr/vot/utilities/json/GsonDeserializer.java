package pm12016g3.tln.univ.fr.vot.utilities.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import pm12016g3.tln.univ.fr.vot.models.SocialChoice;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.json.
 * File GsonDeserializer.java.
 * Created by Ysee on 05/06/2017 - 10:47.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class GsonDeserializer {

    private Gson gson;

    public GsonDeserializer() {
        gson = GsonSingleton.getInstance();
    }

    Type getType(Class<?> rawClass, Class<?> parameter) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {parameter};
            }
            @Override
            public Type getRawType() {
                return rawClass;
            }
            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    public <T> SocialChoice<T> deserialize(final JsonObject rawSocialChoice, final Class<T> dataClass) {
        return gson.fromJson(rawSocialChoice, getType(SocialChoice.class, dataClass));
    }
    public <T> SocialChoice<T> deserialize(final String rawSocialChoice, final Class<T> dataClass) {
        return gson.fromJson(rawSocialChoice, getType(SocialChoice.class, dataClass));
    }
}
