package pm12016g3.tln.univ.fr.vot.features.about;



import android.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Fragment shows about us information
 */
@EFragment(R.layout.about_us_fragment)
public class AboutUsFragment extends Fragment {
    @ViewById(R.id.about_us_tv)
    TextView aboutus;

    @AfterViews
    void init(){
        aboutus.setText(R.string.about_us);
    }

}
