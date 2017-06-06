package pm12016g3.tln.univ.fr.vot.utilities.network;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.ResponseEntity;

import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.Vote;
import pm12016g3.tln.univ.fr.vot.models.network.Response;

/**
 * Created by damienlemenager on 06/06/2017.
 */

@Rest(rootUrl = "http://dapm1-g3-vot.herokuapp.com/api", converters = {MyGsonHttpMessageConverter.class})
@Accept(MediaType.APPLICATION_JSON)
public interface VOTvoteAPI {

    /**
     * Insert vote
     *
     * @return A user, the response data which
     * contains the user authenticated.
     */
    @Post("votes")
    @Header(name = "Content-Type", value = "application/json")
    ResponseEntity<Response<String>> insert(@Body Vote vote);

}
