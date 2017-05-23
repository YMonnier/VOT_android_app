package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.features.root.LoginActivity_;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by wenlixing on 23/05/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NetworkFragmentTest {

    private static final String INPUT_TEXT = "Donut";

    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule = new ActivityTestRule<MainActivity_>(MainActivity_.class);


    public void showFragment(){
        onView(withId(R.id.drawer_layout))
                .perform(open());
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.sidebar_friends));

    }
    /**
     * Test validation of Research EditText
     */
    @Test
    public void researchEditTextInputTest() {
        showFragment();
        onView(withId(R.id.network_input_research))
                .perform(typeText(INPUT_TEXT))
                .check(matches(withText(INPUT_TEXT)));
    }

    /**
     * Test friends ListView show filtered friends
     */
    @Test
    public void friendsListViewFilterTest(){
        showFragment();
        onView(withId(R.id.network_input_research))
                .perform(typeText(INPUT_TEXT));
        onData(allOf(is(instanceOf(NetWorkFragmentItem.class)),withText(INPUT_TEXT)));
    }
}
