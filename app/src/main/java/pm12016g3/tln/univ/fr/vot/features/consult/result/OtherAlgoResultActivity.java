package pm12016g3.tln.univ.fr.vot.features.consult.result;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.result.detail.ResultDetailActivity_;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_other_algo_result_activity)
public class OtherAlgoResultActivity extends AppCompatActivity {
    final String TAG = OtherAlgoResultActivity.class.getSimpleName();
    /**
     * Vote is secret or not
     * If the vote is secret not show the details
     */
    boolean confidentiality;

    @ViewById(R.id.result_tv)
    TextView result_tv;

    @ViewById(R.id.fab_details)
    FloatingActionButton fabDetails;

    @RestService
    VOTSocialChoiceAPI serviceAPI;

    Gson gson;

    SocialChoice socialChoice;

    /**
     * List of data for pie chart
     */
    ArrayList<PieEntry> entries = new ArrayList<>();

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get SocialChoice from Intent
        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        gson = GsonSingleton.getInstance();
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SocialChoice.class);
        Log.d(TAG,"ID "+socialChoice.getId());

        confidentiality = socialChoice.isConfidentiality();
        if(confidentiality){
            fabDetails.setVisibility(View.INVISIBLE);
        }

    }


    /**
     * Go back when you click the go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    /**
     * Click floating action button to show details of the result
     */
    @Click(R.id.fab_details)
    public void onClickFabDetails(){
        startActivity(new Intent(this, ResultDetailActivity_.class));
    }
}
