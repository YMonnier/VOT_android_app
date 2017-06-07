package pm12016g3.tln.univ.fr.vot.utilities.network;

import com.google.gson.JsonObject;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.models.FriendRequest;
import pm12016g3.tln.univ.fr.vot.models.User;
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
public interface VOTFriendsAPI {

    /**
     * Get all friend of the authenticated person..
     *
     * @return A list of Social Choice Object.
     */
    @Get("/users/friends")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<List<User>>> getFriends();

    /**
     * Get all friend of the authenticated person..
     *
     * @return A list of Social Choice Object.
     */
    @Get("/users")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<List<User>>> getUsers();

    /**
     * Get all friend of the authenticated person..
     *
     * @return A list of Social Choice Object.
     */
    @Get("/users/find/{nickname}")
    @RequiresHeader("Authorization")
    ResponseEntity<Response<List<User>>> findUserByPseudo(@Path final String nickname);


    /**
     * Sends friend requests
     *
     * @param friendRequest a firend request
     * @return A response
     */
    @Post("/social_relations/friend_request")
    @RequiresHeader("Authorization")
    Response requests(@Body FriendRequest friendRequest);

    /**
     * Update a friend request
     *
     * @param answer true if the user confirm, otherwise, false.
     * @return A response
     */
    @Put("/social_relations/{id}/confirm/{answer}")
    @RequiresHeader("Authorization")
    ResponseEntity<JsonObject> answer(@Path Long id, @Path boolean answer);

    /**
     * Set a specific header to the HTTP request.
     *
     * @param name  header name
     * @param value header value
     */
    void setHeader(String name, String value);

    String getHeader(String name);
}
