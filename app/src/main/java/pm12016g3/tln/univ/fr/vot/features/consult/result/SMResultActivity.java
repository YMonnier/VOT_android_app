package pm12016g3.tln.univ.fr.vot.features.consult.result;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.Settings;
import pm12016g3.tln.univ.fr.vot.features.consult.result.detail.ResultDetailActivity_;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.network.Response;
import pm12016g3.tln.univ.fr.vot.models.result.Result;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.JsonKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.network.VOTSocialChoiceAPI;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_sm_result_activity)
public class SMResultActivity extends AppCompatActivity {
    final String TAG = SMResultActivity.class.getSimpleName();
    /**
     * Vote is secret or not
     * If the vote is secret not show the details
     */
    boolean confidentiality;

    /**
     * The pie chart
     */
    @ViewById(R.id.pie_chart)
    PieChart pieChart;

    @ViewById(R.id.fab_details)
    FloatingActionButton fabDetails;

    @RestService
    VOTSocialChoiceAPI serviceAPI;

    Gson gson;

    JsonObject jsonObject;

    SocialChoice<SCSMajorityBallot> socialChoice;

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
        socialChoice = gsonDeserializer.deserialize(strObj, SCSMajorityBallot.class);
        confidentiality = socialChoice.isConfidentiality();
        if (confidentiality) {
            fabDetails.setVisibility(View.INVISIBLE);
        }


        showPieChart();
    }

    /**
     * To display PieChart
     */
    void showPieChart() {
        // general configuration
        pieChart.setUsePercentValues(true);
        pieChart.setDescription(null);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(35f);

        /*--- Setting data ---*/
        entries.add(new PieEntry(18.5f, "John"));
        entries.add(new PieEntry(26.7f, "Mark"));
        entries.add(new PieEntry(24.0f, "Dark"));
        entries.add(new PieEntry(30.8f, "Bil"));
        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);
        //Set the Text Size and Text Color
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setData(data);
        pieChart.invalidate();

        //Adding Animations
        pieChart.animateXY(1400, 1400);
    }

    /**
     * Go back when you click the go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow() {
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    /**
     * Click floating action button to show details of the result
     */
    @Click(R.id.fab_details)
    public void onClickFabDetails() {
        startActivity(new Intent(this, ResultDetailActivity_.class));
    }

    @Background
    void getResult() {
        try {
            serviceAPI.setHeader(JsonKeys.AUTHORIZATION, Settings.currentUser.getAccessToken());
            ResponseEntity<Response<Result>> responseEntity = serviceAPI.getResultat(socialChoice.getId());
            Log.d(TAG, "Response : " + responseEntity.toString());

            if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
                /*Snack.showFailureMessage(getView(),
                        getString(R.string.snack_error_http_400_500),
                        Snackbar.LENGTH_LONG);*/
            }
        } catch (RestClientException e) {
            Log.d(TAG, e.getLocalizedMessage());
        }

    }
}
