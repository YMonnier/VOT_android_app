package pm12016g3.tln.univ.fr.vot.features.create;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.EspressoUtils;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.SettingsFragment_;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by wenlixing on 23/05/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SettingFragmentTest {
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
                .replace(R.id.mainContent, new SettingsFragment_())
                .commitAllowingStateLoss();
    }

    @Test
    public void titleEmptyTest() {
        onView(withId(R.id.input_title)).perform(typeText(EMPTY_VALUE), closeSoftKeyboard());
        onView(withId(R.id.input_desc)).perform(typeText(TEXT), closeSoftKeyboard());
        onView(withId(R.id.algorithms)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(ALGO))).perform(click());
        onView(withId(R.id.menu_item_next_arrow)).perform(click());
        onView(withId(R.id.input_title))
                .check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.error_field_required))));
    }

    @Test
    public void descriptionEmptyTest() {
        onView(withId(R.id.input_title)).perform(typeText(TEXT), closeSoftKeyboard());
        onView(withId(R.id.input_desc)).perform(typeText(EMPTY_VALUE), closeSoftKeyboard());
        onView(withId(R.id.algorithms)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(ALGO))).perform(click());
        onView(withId(R.id.menu_item_next_arrow)).perform(click());
        onView(withId(R.id.input_desc))
                .check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.error_field_required))));
    }

}
