package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.location;

import android.app.Fragment;
import android.widget.SeekBar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SeekBarProgressChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.location.
 * File LocationFriendsFragment.java.
 * Created by Ysee on 30/05/2017 - 09:39.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_invitation_location_fragment)
public class LocationFriendsFragment extends Fragment {
    private static final String TAG = LocationFriendsFragment.class.getSimpleName();

    @ViewById(R.id.seekBar)
    SeekBar seekBar;

    @ViewById(R.id.radius_label)
    TextView radiusLabel;

    /**
     * Minimum radius.
     */
    private final int MIN_RADIUS = 5;

    /**
     * Maximum radius.
     */
    private final int MAX_RADIUS = 500;

    /**
     * Step radius.
     */
    private final int STEP_RADIUS = 1;

    /**
     * The current radius.
     */
    private int currentRadius = MIN_RADIUS;

    @AfterViews
    void init() {
        seekBar.setMax((MAX_RADIUS - MIN_RADIUS) / STEP_RADIUS);
        seekBar.setProgress(MIN_RADIUS);
        radiusLabel.setText(String.valueOf(currentRadius));
    }

    @SeekBarProgressChange(R.id.seekBar)
    void onProgressChangeOnSeekBar(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            updateProgress(progress);
            updateRadiusLabel();
        }
    }

    @UiThread
    void updateProgress(int progress) {

        if (progress == 0) {
            currentRadius = MIN_RADIUS;
        } else if (progress > 0) {
            currentRadius = progress + MIN_RADIUS;
        }
        seekBar.setProgress(currentRadius);
    }

    @UiThread
    void updateRadiusLabel() {
        radiusLabel.setText(String.valueOf(currentRadius));
    }
}
