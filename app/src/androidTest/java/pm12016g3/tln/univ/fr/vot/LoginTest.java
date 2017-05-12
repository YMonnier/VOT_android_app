package pm12016g3.tln.univ.fr.vot;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.features.root.LoginActivity_;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by wenlixing on 12/05/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    private final String shortPassword = "123";

    @Rule
    public ActivityTestRule<LoginActivity_> mActivityRule = new ActivityTestRule<LoginActivity_>(LoginActivity_.class);

    @Test
    public void loginWithSortPassword(){
        onView(withId(R.id.input_password)).perform(typeText(shortPassword),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_password)).check(matches(hasErrorText("Le mot de passe est trop court")));
    }




}
