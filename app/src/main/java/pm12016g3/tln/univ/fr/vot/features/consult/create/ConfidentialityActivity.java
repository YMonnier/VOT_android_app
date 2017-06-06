package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import org.androidannotations.annotations.EActivity;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Created by Jeremy on 06/06/2017.
 */

@EActivity
public class ConfidentialityActivity extends IntroActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new FragmentSlide.Builder()
                .background(R.color.vot_blue)
                        //.backgroundDark(R.color.vot_orange_dark)
                .fragment(R.layout.confidentiality, R.style.AppTheme)
                .build());
    }
}
