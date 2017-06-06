package pm12016g3.tln.univ.fr.vot.features.create.algorithms.simple;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.EspressoUtils;
import pm12016g3.tln.univ.fr.vot.features.MainActivity_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.SettingsFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.SimpleVoteFragment_;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by wenlixing on 23/05/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleVoteFragmentTest {
    private static final String NBCHOICE = "2";
    private static final String EMPTY_VALUE = "";
    private static final String CHOICE1 = "Hello";
    private static final String CHOICE2 = "Type";
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
    public void nbchoiceEmptyTest() {
        /*
        onView(withId(R.id.input_nb_choices)).perform(typeText(EMPTY_VALUE), closeSoftKeyboard());
        onView(withId(R.id.input_candidat)).perform(typeText(CHOICE1), closeSoftKeyboard());
        onView(withId(R.id.menu_item_next_arrow)).perform(click());
        onView(withId(R.id.input_nb_choices))
                .check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.error_field_required))));*/
    }


}
