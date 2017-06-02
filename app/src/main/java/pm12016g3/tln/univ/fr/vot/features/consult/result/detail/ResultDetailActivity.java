package pm12016g3.tln.univ.fr.vot.features.consult.result.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.result.ResultActivity;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchActivity;
import pm12016g3.tln.univ.fr.vot.features.network.research.NetworkResearchListAdapter;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

@EActivity(R.layout.consult_result_detail_activity)
public class ResultDetailActivity extends AppCompatActivity {
    private final String TAG = ResultActivity.class.getSimpleName();
    /**
     * EditText to Research results
     */
    @ViewById(R.id.result_details_input_research)
    EditText research;

    /**
     * ListView to show Researched results
     */
    @ViewById(R.id.result_details_list)
    ListView resultsListView;

    /**
     * Adapter for ListView
     */
    @Bean
    ResultDetailListAdapter adapter;

    /**
     * List of results
     */
    List<ResultDetailItem> results = new ArrayList<>();

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        results.add(new ResultDetailItem("John","A"));
        results.add(new ResultDetailItem("Paul","B"));
        results.add(new ResultDetailItem("Jack","A"));
        adapter.addAll(results);
        resultsListView.setAdapter(adapter);

    }


    /**
     * Go back when click go back button
     */
    @OptionsItem(android.R.id.home)
    public void onClickUpArrow(){
        ViewUtils.closeKeyboard(this, getCurrentFocus());
        finish();
    }

    /**
     * Listen to research EditText text change then update ListView display
     * @param text
     * @param hello
     * @param before
     * @param start
     * @param count
     */
    @TextChange(R.id.result_details_input_research)
    void onTextChanges(CharSequence text,
                       TextView hello,
                       int before,
                       int start,
                       int count) {
        adapter.clear();
        if(text.length() == 0){
            adapter.addAll(results);
        }else if(text.length() !=0){
            for (ResultDetailItem r : results) {
                if (r.getVoter().toLowerCase().startsWith(text.toString().toLowerCase())) {
                    adapter.add(r);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }


}
