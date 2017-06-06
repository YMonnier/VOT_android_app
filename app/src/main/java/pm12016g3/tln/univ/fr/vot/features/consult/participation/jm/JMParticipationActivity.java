package pm12016g3.tln.univ.fr.vot.features.consult.participation.jm;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.stv.STVParticipationActivity;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.JMVote;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.shared.SCMajorityJudgment;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.utilities.ExtraKeys;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonSingleton;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;

/**
 * Created by damienlemenager on 06/06/2017.
 */
@EActivity(R.layout.consult_participation_jm_participation_activity)
@OptionsMenu(R.menu.consult_participation_participation_bar)
public class JMParticipationActivity extends AppCompatActivity {

    final String TAG = JMParticipationActivity.class.getSimpleName();
    private final String TV_STRING = "Veuillez choisir une étiquette pour chaque choix.\nLes choix :";
    SocialChoice<SCMajorityJudgment> socialChoice;

    @ViewById(R.id.tv_reference)
    TextView tv_reference;

    @ViewById(R.id.vote_description)
    TextView vote_description;

    @ViewById(R.id.ll_candidats)
    LinearLayout ll_candidats;

    @ViewById(R.id.lv_jm_candidats)
    ListView lv_jm_candidats;

    @Bean
    JMParticipationAdapter adapter;

    /**
     * Initialisation after the views binding has happened
     */
    @AfterViews
    void init() {

        String strObj = getIntent().getStringExtra(ExtraKeys.SOCIAL_CHOICE);
        Gson gson = GsonSingleton.getInstance();
        GsonDeserializer gsonDeserializer = new GsonDeserializer();
        socialChoice = gsonDeserializer.deserialize(strObj, SCMajorityJudgment.class);

        System.out.println(" obj : "+socialChoice);

        this.setTitle(socialChoice.getTitle());

        tv_reference.setText(TV_STRING);
        vote_description.setText(socialChoice.getDescription());


        JMCandidat jmCandidat = null;
        List<JMCandidat> list = new ArrayList<>();

        for (Candidat candidat : socialChoice.getCandidats()) {
            jmCandidat = new JMCandidat();
            jmCandidat.setName(candidat.getName());
            jmCandidat.setLabels(socialChoice.getData().getLabels());
            list.add(jmCandidat);
        }

        adapter.getItems().addAll(list);
        lv_jm_candidats.setAdapter(adapter);

        //added LayoutParams
        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayoutTMP = null;
        TextView textView = null;

        Spinner spinner = null;

        ArrayAdapter<String> spinnerArrayAdapter = null;


        for (Candidat candidat : socialChoice.getCandidats()) {

            linearLayoutTMP = new LinearLayout(this);
            linearLayoutTMP.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutTMP.setLayoutParams(params);
            linearLayoutTMP.setId((candidat.getId().intValue())+9);

            //add textView
            textView = new TextView(this);
            textView.setTextSize(20);
            textView.setText(candidat.getName()+ " : ");
            textView.setId((candidat.getId().intValue())+10);
            textView.setPadding(100,5,10,5);

            linearLayoutTMP.addView(textView);

            spinner = new Spinner(this);
            List<String> list = new ArrayList<>();

            for (String str : socialChoice.getData().getLabels())
                list.add(str);
            spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setLayoutParams(params);
            spinner.setId((candidat.getId().intValue())+11);
            System.out.println("id spinner : "+(candidat.getId().intValue())+11);
            linearLayoutTMP.addView(spinner);
            ll_candidats.addView(linearLayoutTMP);

        }*/


    }

    /**
     * Listen to the click of the check button on the menu bar
     */
    @OptionsItem(R.id.participation_action_check)
    public void onClickCheckmark(){
        System.out.println("jclique ici");
        JMVote jmVote = new JMVote(socialChoice.getId());

        for (int i = 0; i< socialChoice.getCandidats().size(); i++) {
            JMCandidat jmCandidat = adapter.getItem(i);

        }

        finish();
    }

}
