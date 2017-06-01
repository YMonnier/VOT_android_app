package pm12016g3.tln.univ.fr.vot.features.consult.result;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by wenlixing on 22/05/2017.
 */

@EActivity(R.layout.consult_result_activity)
public class ResultActivity extends AppCompatActivity {
    final String TAG = ResultActivity.class.getSimpleName();

    @ViewById(R.id.pie_chart)
    PieChart pieChart;
    ArrayList<PieEntry> entries = new ArrayList<>();
    @AfterViews
    void init() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showPieChart();
    }

    /**
     * To display PieChart
     */
    void showPieChart(){
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
    public void onClickUpArrow(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

}
