package pm12016g3.tln.univ.fr.vot.features.root;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.EspressoUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by wenlixing on 12/05/2017.
 */

public class RegistrationActivityTest {


    private static final String VALID_EMAIL = "test@gmail.com";
    private static final String NOT_VALID_EMAIL = "test@test";
    private static final String VALID_PASSWORD = "password";
    private static final String NOT_VALID_PASSWORD = "abcd";
    private static final String EMPTY_VALUE = "";

    @Rule
    public ActivityTestRule<RegistrationActivity_> mActivityRule = new ActivityTestRule<RegistrationActivity_>(RegistrationActivity_.class);

    /**
     * Check if error display if email is empty
     */
    @Test
    public void emptyEmailTest(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(EMPTY_VALUE),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_email)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.registration_error_field_required))));
    }

    /**
     * Check if error display if password is empty
     */
    @Test
    public void emptyPasswordTest(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(EMPTY_VALUE),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_password)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.registration_error_field_required))));
    }

    /**
     * Check if error display if confirmed password is empty
     */
    @Test
    public void emptyConfirmedPasswordTest(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(EMPTY_VALUE),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_confirmation_password)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.registration_error_field_required))));
    }
    /**
     * Check if EmailValidator is valid
     */
    @Test
    public void invalidEmailTest(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(NOT_VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_email)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.registration_error_invalid_email))));
    }

    /**
     * Check if PasswordValidator is valid
     */
    @Test
    public void invalidPasswordTest(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(NOT_VALID_PASSWORD),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(NOT_VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_password)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.registration_error_invalid_password))));
    }

    /**
     * Check if error display when confirmed password is not the same
     */
    @Test
    public void wrongConfirmedPassword(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(NOT_VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
        onView(withId(R.id.input_confirmation_password)).check(matches(EspressoUtils.withError(mActivityRule.getActivity().getString(R.string.registration_error_not_matching_password))));
    }

    @Test
    public void registrationSuccessTest(){
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(VALID_EMAIL),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.input_confirmation_password)).perform(typeText(VALID_PASSWORD),closeSoftKeyboard());
        onView(withId(R.id.btn_confirmation)).perform(click());
    }
}
