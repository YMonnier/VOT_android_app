package pm12016g3.tln.univ.fr.vot.utilities.network;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.ResponseEntity;

import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.network.
 * File VOTAuthAPI.java.
 * Created by Ysee on 31/05/2017 - 14:02.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Rest(rootUrl = "http://dapm1-g3-vot.herokuapp.com/api/", converters = {MyGsonHttpMessageConverter.class})
//@Rest(rootUrl = "http://127.0.0.1:9998/api", converters = {MyGsonHttpMessageConverter.class})
@Accept(MediaType.APPLICATION_JSON)
public interface VOTAuthAPI {

    /**
     * Login action to authenticate through
     * VOT API.
     *
     * @return A user, the response data which
     * contains the user authenticated.
     */
    @Post("users/login")
    @Header(name = "Content-Type", value = "application/json")
    ResponseEntity<Response<User>> login(@Body User currentUser);

    /**
     * Register action. Create a user to the VOT system.
     *
     *
     * @param user user information
     * @return A user object.
     */
    @Post("users")
    @Header(name = "Content-Type", value = "application/json")
    ResponseEntity<Response<User>> registration(@Body User user);


    /**
     * Update the device token of the current user action.
     *
     * @param user user information
     * @return A user object.
     */
    @Post("users/device_token")
    @Header(name = "Content-Type", value = "application/json")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<User>> deviceToken(@Body User user);


    /**
     * Set a specific header to the HTTP request.
     *
     * @param name  header name
     * @param value header value
     */
    void setHeader(String name, String value);

    String getHeader(String name);
}
