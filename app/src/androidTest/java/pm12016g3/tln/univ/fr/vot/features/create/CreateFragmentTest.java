package pm12016g3.tln.univ.fr.vot.features.create;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.SettingsFragment_;

/**
 * Created by wenlixing on 06/06/2017.
 */
@RunWith(AndroidJUnit4.class)
public class CreateFragmentTest {
    private static final String TEXT = "test";
    private static final String EMPTY_VALUE = "";
    private static final String ALGO = "STV";
    private static final String DEFAULT_ALGO = "Type";
    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule =
            new ActivityTestRule<MainActivity_>(MainActivity_.class);

    @Before
    public void setup() {
        mActivityRule.getActivity()
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, new CreateFragment_())
                .commitAllowingStateLoss();
    }

    @Test
    public void test(){

    }
}
