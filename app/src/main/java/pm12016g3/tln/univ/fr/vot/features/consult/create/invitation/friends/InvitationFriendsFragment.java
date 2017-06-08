package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends;

import android.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import com.annimon.stream.Stream;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.InvitationFragment;
import pm12016g3.tln.univ.fr.vot.models.User;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.loader.LoaderDialog;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTFriendsAPI;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.friends.
 * File InvitationFriendsFragment.java.
 * Created by Ysee on 29/05/2017 - 15:04.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_invitation_friends_fragment)
public class InvitationFriendsFragment extends Fragment {
    private static final String TAG = InvitationFriendsFragment.class.getSimpleName();
    /**
     * Friend list.
     */
    @ViewById(R.id.listView)
    ListView listView;

    /**
     * Friend list adapter.
     */
    @Bean
    InvitationFriendsFragmentListAdapter adapter;

    /**
     * Parent view.
     */
    InvitationFragment parent;

    /**
     * Progress view
     */
    LoaderDialog progressView;

    @RestService
    VOTFriendsAPI serviceAPI;

    @AfterViews
    void init() {
        progressView = new LoaderDialog(getActivity(), "");
        parent = (InvitationFragment) getParentFragment();
        loadData();
    }

    @ItemClick(R.id.listView)
    void clickOnListView(User item) {
        item.setSelected(!item.isSelected());
        adapter.notifyDataSetChanged();

        List<User> list = (List<User>)parent.getParent().getSocialChoice().getParticipants();

        if (!list.contains(item)) {
            list.add(item);
        } else {
            Stream.of(list)
                    .filter(value -> value.getPseudo().equals(item.getPseudo()))
                    .forEach(list::remove);
        }
    }

    /**
     * Function allowing to get information
     * from server and put them to the adapter
     * <p>
     * This task is done into the background thread.
     */
    @Background
    void loadData() {
        try {
            showProgress();
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<List<User>>> response = serviceAPI.getFriends();
            Log.d(TAG, response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                List<User> users = response.getBody().getData();
                Log.d(TAG, Arrays.toString(users.toArray()));
                adapter.getItems()
                        .addAll(users);

                setAdapter();
                dismissProgress();
            } else {
                dismissProgress();
            }
        } catch (RestClientException e) {
            dismissProgress();
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    /**
     * Show the progress view.
     * This task is done on UI Thread.
     */
    @UiThread
    void showProgress() {
        progressView.show();
    }

    /**
     * Dismiss the progress view.
     * This task is done on UI Thread.
     */
    @UiThread
    void dismissProgress() {
        progressView.dismiss();
    }

    /**
     * Set adapter
     * This task is done on UI Thread.
     */
    @UiThread
    void setAdapter() {
        listView.setAdapter(adapter);
    }
}
