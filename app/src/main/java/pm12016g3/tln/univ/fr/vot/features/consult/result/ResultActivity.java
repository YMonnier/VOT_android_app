package pm12016g3.tln.univ.fr.vot.features.consult.result;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.stv.ParticipationItem;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.stv.ParticipationListAdapter;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

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
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    @OptionsItem(R.id.network_research_action_check)
    public void onClickCheckmark(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }


}