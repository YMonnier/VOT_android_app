package pm12016g3.tln.univ.fr.vot.utilities.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.Arrays;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.network.
 * File MyGsonHttpMessageConverter.java.
 * Created by Ysee on 31/05/2017 - 14:04.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class MyGsonHttpMessageConverter extends GsonHttpMessageConverter {
    /**
     * Override MediaType for GsonHttpMessageConverter
     */
    public MyGsonHttpMessageConverter() {
        List<MediaType> types = Arrays.asList(
                new MediaType("text", "html", DEFAULT_CHARSET),
                new MediaType("application", "json", DEFAULT_CHARSET),
                new MediaType("application", "*+json", DEFAULT_CHARSET)
        );

        Gson customGson = new GsonBuilder()
                .setDateFormat(GsonSingleton.DATE_FORMAT)
                .create();

        super.setGson(customGson);
        super.setSupportedMediaTypes(types);
    }
}
