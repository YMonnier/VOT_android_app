package pm12016g3.tln.univ.fr.vot.features.consult.participation.jm;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import org.androidannotations.annotations.EBean;

import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.ListViewAdapterBase;

/**
 * Created by damienlemenager on 07/06/2017.
 */
@EBean
public class JMParticipationListAdapter extends ListViewAdapterBase<Candidat, JMParticipationItemView>
        implements Spinner.OnItemSelectedListener {

    private final String TAG = JMParticipationListAdapter.class.getSimpleName();
    private static final int SPINNER_TAG = 150;

    @Override
    protected JMParticipationItemView onCreateItemView(ViewGroup parent) {
        return JMParticipationItemView_.build(parent.getContext());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        JMParticipationItemView v = (JMParticipationItemView) super.getView(i, view, viewGroup);

        v.s_labels.setTag(SPINNER_TAG + i);
        v.s_labels.setOnItemSelectedListener(this);

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) view.getParent();
        int itemID = ((int) spinner.getTag()) - SPINNER_TAG;
        getItems().get(itemID).setLabelSelected((String) spinner.getSelectedItem());
        Log.d(TAG, spinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
