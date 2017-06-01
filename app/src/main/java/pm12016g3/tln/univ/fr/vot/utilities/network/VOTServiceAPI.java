package pm12016g3.tln.univ.fr.vot.utilities.network;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.models.SocialChoice;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.network.
 * File VOTServiceAPI.java.
 * Created by Ysee on 01/06/2017 - 09:53.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Rest(rootUrl = "http://dapm1-g3-vot.herokuapp.com/api", converters = {MyGsonHttpMessageConverter.class})
@Accept(MediaType.APPLICATION_JSON)
public interface VOTServiceAPI {

    /**
     * Get all social choice.
     *
     * @return A list of Social Choice Object.
     */
    @Get("/social_choices")
    @RequiresHeader("Authorization")
    ResponseEntity<List<SocialChoice>> getSocialChoices();
}
