package pm12016g3.tln.univ.fr.vot.features;

import android.app.Fragment;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by wenlixing on 23/05/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule = new ActivityTestRule<>(MainActivity_.class);

    @Before
    public void  setUp(){
        Fragment fragment = new CreateFragment_();
        mActivityRule.getActivity()
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainCreateContent, new CreateFragment_())
                .commitAllowingStateLoss();
    }

    /**
     * Test whether NetworkFragment shows after clicking on Friends item on navigation drawer
     */
    @Test
    public void clickOnYourNavigationItemFriends(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.sidebar_friends));
        onView(withId(R.id.network_fragment)).check(matches(isDisplayed()));
    }
   // User(id=153, email=emmily929@gmail.com, pseudo=wenli01, picture=, longitude=0.0, latitude=0.0, selected=false, accessToken=eyJhbGciOiJSUzI1NiIsImtpZCI6IjBkNDI5Y2I1M2Q4YTFlZjA5ZjFhZTY1ODc1N2JlMjFlNzZhNjEzN2IifQ.eyJhenAiOiI3MjQ4OTM2MjQ1NjctdTFrZm1rbmJmcTI0a2Rlbm1jYmxib2I5Ym9pZnUxYmkuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3MjQ4OTM2MjQ1NjctcmNwOHBkdDQ5Z2FuYWNxYzAzYXJwZ2g2bzkyaGY1cjIuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTMwMDU2ODEwMTk2NjgyNzU0MTEiLCJlbWFpbCI6ImVtbWlseTkyOUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiYWNjb3VudHMuZ29vZ2xlLmNvbSIsImlhdCI6MTQ5Njc1Mjc5NywiZXhwIjoxNDk2NzU2Mzk3fQ.squoc--zbGOnxLMNWzqeaSISX9zIsohleQo-mC1zhn833EAeQd8kJzt5_jQwoAqBR-lUX7X4vwssg1HLcdHDU-FpkNSPP3EXJenQTSQiaw8QkcQoeSkxPjbf0W3bqVA0AIso8WmneanhWCs4slI61IeZUGRM63ZwjdIPMgOaGDxWUcHL8OO2BNf4Ru-yDCLfY8QRm1Lf5joP1eR9d6ls8qLMYAnaCurWoEB0N5GwvhNjQHkskpK2yH4tqPOSbLd4u-fBEozVJm1HOtfHJXN8rryOz9wAxgidbcVnQri1vhGoxyxt0r8-_1mkgX9K8e444cOjcHp2z1JJJ5xATIgz1Q, deviceToken=null)
}
