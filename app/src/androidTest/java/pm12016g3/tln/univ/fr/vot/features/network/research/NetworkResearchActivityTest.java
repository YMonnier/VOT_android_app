package pm12016g3.tln.univ.fr.vot.features.network.research;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by wenlixing on 23/05/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NetworkResearchActivityTest {
    private static final String INPUT_TEXT = "Jack";
    @Rule
    public ActivityTestRule<NetworkResearchActivity_> mActivityRule = new ActivityTestRule<NetworkResearchActivity_>(NetworkResearchActivity_.class);

    /**
     * Test validation of Research EditText
     */
    @Test
    public void researchEditTextInputTest() {
        onView(withId(R.id.network_research_input_research))
                .perform(typeText(INPUT_TEXT))
                .check(matches(withText(INPUT_TEXT)));
    }

    /**
     * Test friends ListView show filtered friends
     */
    @Test
    public void friendsListViewFilterTest(){
        onView(withId(R.id.network_research_input_research))
                .perform(typeText(INPUT_TEXT));
        onData(allOf(is(instanceOf(BasicItem.class)),withText(INPUT_TEXT)));
    }
}
