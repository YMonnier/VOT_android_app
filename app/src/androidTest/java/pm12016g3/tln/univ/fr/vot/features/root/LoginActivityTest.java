package pm12016g3.tln.univ.fr.vot.features.root;


import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.EspressoUtils;
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
public class LoginActivityTest {

    private static final String VALID_EMAIL = "test@gmail.com";
    private static final String NOT_VALID_EMAIL = "test@test";
    private static final String VALID_PASSWORD = "password";
    private static final String NOT_VALID_PASSWORD = "abcd";
    private static final String EMPTY_VALUE = "";

    @Rule
    public ActivityTestRule<LoginActivity_> mActivityRule = new ActivityTestRule<LoginActivity_>(LoginActivity_.class);

    /**
     * Check if error display if email is empty
     */
    @Test
    public void loginWithEmptyEmail(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(EMPTY_VALUE),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_email)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.login_error_field_required))));
    }

    /**
     * Check if error display if password is empty
     */
    @Test
    public void loginWithEmptyPassword(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(EMPTY_VALUE),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_password)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.login_error_field_required))));
    }

    /**
     * Check if EmailValidator is valid
     */
    @Test
    public void loginWithInvalidEmail(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(NOT_VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_email)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.login_error_invalid_email))));
    }

    /**
     * Check if PasswordValidator is valid
     */
    @Test
    public void loginWithInvalidPassword(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(NOT_VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_password)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.login_error_invalid_password))));
    }

    /**
     * Test all fields of the view
     */
    @Test
    public void loginWithSucess(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
    }


}
