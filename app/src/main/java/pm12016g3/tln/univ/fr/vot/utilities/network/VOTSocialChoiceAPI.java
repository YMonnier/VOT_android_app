package pm12016g3.tln.univ.fr.vot.utilities.network;

import com.google.gson.JsonObject;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.models.Vote;
import pm12016g3.tln.univ.fr.vot.models.network.Response;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.network.
 * File VOTSocialChoiceAPI.java.
 * Created by Ysee on 01/06/2017 - 09:53.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Rest(rootUrl = "http://dapm1-g3-vot.herokuapp.com/api", converters = {MyGsonHttpMessageConverter.class})
@Accept(MediaType.APPLICATION_JSON)
public interface VOTSocialChoiceAPI {

    /**
     * Get all social choice.
     *
     * @return A list of Social Choice Object.
     */
    @Get("/social_choices/current")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<List<JsonObject>>> getSocialChoices();


    @Get("/social_choices/{id}")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<JsonObject>> getSocialChoice(@Path Long id);

    @Post("/votes")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<JsonObject>> vote(@Body Vote vote);

    /**
     * Set a specific header to the HTTP request.
     *
     * @param name  header name
     * @param value header value
     */
    void setHeader(String name, String value);

    String getHeader(String name);
}
