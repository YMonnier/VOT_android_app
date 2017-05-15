package pm12016g3.tln.univ.fr.vot.features;

/**
 * Created by wenlixing on 12/05/2017.
 */

import android.view.View;
import android.widget.EditText;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class EspressoUtils {
    /**
     * Match if error display in EditText
     *
     * @param expected error message expected
     * @return true if error message expected is equal, otherwise false
     */
    public static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                EditText editText = (EditText) view;
                return editText.getError().toString().equals(expected);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    public static <T> Matcher<T> withMyValue(final String name) {
        return new BaseMatcher<T>() {
            @Override
            public boolean matches(Object item) {
                return item.toString().equals(name);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
