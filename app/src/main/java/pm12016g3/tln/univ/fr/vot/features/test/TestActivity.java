package pm12016g3.tln.univ.fr.vot.features.test;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.custom.LifelineView;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.test.
 * File TestActivity.java.
 * Created by Ysee on 19/05/2017 - 13:19.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EActivity(R.layout.test_test_activity)
public class TestActivity extends AppCompatActivity {

    @ViewById(R.id.lifelineView)
    LifelineView lifelineView;

    @AfterViews
    void init() {
        lifelineView.setSelected(6);
    }

}
