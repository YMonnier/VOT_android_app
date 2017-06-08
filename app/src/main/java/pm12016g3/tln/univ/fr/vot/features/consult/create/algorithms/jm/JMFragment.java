package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.jm;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.annimon.stream.Stream;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;
import pm12016g3.tln.univ.fr.vot.features.consult.create.Validable;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.SimpleVoteFragmentListAdapter;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.InvitationFragment_;
import pm12016g3.tln.univ.fr.vot.features.shared.AnimatedButton;
import pm12016g3.tln.univ.fr.vot.features.shared.AnimatedButton_;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.Label;
import pm12016g3.tln.univ.fr.vot.models.shared.SCMajorityJudgment;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.fragment.AppFragment;
import pm12016g3.tln.univ.fr.vot.utilities.views.list.BasicItem;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.
 * File SimpleVoteFragment.java.
 * Created by Ysee on 22/05/2017 - 15:10.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_algo_jm_fragment)
@OptionsMenu(R.menu.consult_create_menu_two_arrows)
public class JMFragment extends AppFragment
        implements View.OnClickListener, Validable {
    private static final String TAG = JMFragment.class.getSimpleName();
    private final int ADD_BUTTON_TAG = 143;
    private final int TRASH_BUTTON_TAG = 243;

    @ViewById(R.id.input_candidat)
    EditText inputCandidat;

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById(R.id.form)
    LinearLayout form;

    /**
     * Add button.
     * Can be null
     */
    AnimatedButton addButton;

    /**
     * Trash button.
     * Can be null
     */
    AnimatedButton trashButton;

    /**
     * Simple Vote Adapter.
     */
    @Bean
    SimpleVoteFragmentListAdapter adapter;

    @Bean
    JMLabelListAdapter JMadapter;

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    String [] labels = new String []{"TB","B","AB","SA","P","I","AR"};
    List<Label> labelList = new ArrayList<>();

    private int labelNb;

    @ViewById(R.id.lv_labels)
    ListView lvLabels;

    @ViewById(R.id.s_labels)
    Spinner sLabels;

    @AfterViews
    void init() {
        Log.d(TAG, "Init");

        initData();

        sLabels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                initData();
                Log.e("klkl", "klkl");

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                Log.e("klkl", "klkl");

            }
        });

        fragmentTitle = getString(R.string.fragment_title_sm);
        parent = (CreateFragment) getParentFragment();
        listView.setAdapter(adapter);
    }

    /**
     * Go to the Invitation view.
     * <p>
     * Check if all data is available and valid.
     * If it is not that case, the App should dsplay a message error.
     */
    @OptionsItem(R.id.menu_item_next_arrow)
    void next() {
        Log.d(TAG, "Next button");
        if(validate())
            setData();
        parent.nextStep(this, new InvitationFragment_());

    }

    @OptionsItem(R.id.menu_item_back_arrow)
    void previous() {
        Log.d(TAG, "Back button");
        parent.previousStep();
    }

    /**
     * Handler when input text change.
     *
     * @param text,  input text
     * @param hello, source view
     */
    @TextChange(R.id.input_candidat)
    void onTextChangesOnHelloTextView(CharSequence text, TextView hello, int before, int start, int count) {
        Log.d(TAG, String.valueOf(text));
        if (text.length() > 0 && form.getChildCount() == 1) {
            addPlusButton();
        } else if (text.length() == 0) {
            removePlusButton();
        }
    }

    @ItemClick(R.id.listView)
    void listViewOnClick(BasicItem item) {
        Log.d(TAG, "Item clicked... " + String.valueOf(item));
    }

    /**
     * Handle when user does a long tap on the list view.
     * Delete the item selected.
     *
     * @param item item selected.
     */
    @ItemLongClick(R.id.listView)
    void listViewOnLongClick(BasicItem item) {

    }

    /**
     * Handler when user tap on the buttons (trash/add).
     *
     * @param view button clicked
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        AnimatedButton button = (AnimatedButton) view;
        if (button != null) {
            if (button.getTag().equals(ADD_BUTTON_TAG)) {
                /*if (dateValidate()) {
                    String candidat = inputCandidat.getText().toString();
                    updateList(candidat);
                }*/
                String candidat = inputCandidat.getText().toString();
                updateList(candidat);
            } else if (button.getTag().equals(TRASH_BUTTON_TAG)) {

                adapter.getItems()
                        .removeAll(
                                Stream.of(adapter.getItems())
                                        .filter(Candidat::isSelected)
                                        .toList());

                adapter.notifyDataSetChanged();
                removeTrashButton();
            }
        }

    }

    /**
     * @return true if we should display
     * trash button, otherwise false
     */
    private boolean shouldShowTrashButton() {
        boolean res = false;
        int i = 0;
        List<Candidat> list = adapter.getItems();
        while (i < list.size() && !res) {
            if (list.get(i).isSelected())
                res = true;
            i += 1;
        }
        return res;
    }

    /**
     * Add an item into the current listView.
     *
     * @param string string content.
     */
    @UiThread
    void updateList(String string) {
        Log.d(TAG, "Add candidat... " + string);
        ViewUtils.closeKeyboard(getActivity(), getActivity().getCurrentFocus());
        Candidat item = new Candidat(string);
        if (!adapter.getItems().contains(item)) {
            adapter.add(item);
            adapter.notifyDataSetChanged();
            inputCandidat.setText(null);
        } else
            Snack.showFailureMessage(getView(), "Candidat déjà existant!", Snackbar.LENGTH_LONG);
    }

    /**
     * Update the current view by settings error
     * message to the input view.
     *
     * @param view  input view
     * @param error error message
     */
    @UiThread
    void updateErrorUi(final EditText view, final String error) {
        view.setError(error);
    }

    /**
     * Reset all error input view
     */
    @UiThread
    void resetErrorUi() {
        inputCandidat.setError(null);
    }

    /**
     * Add the trash button if does not exist.
     */
    @UiThread
    void addTrashButton() {
        if (trashButton == null) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(85,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 5, 0, 5);
            AnimatedButton button = AnimatedButton_.build(getActivity());
            button.setBackgroundResource(R.drawable.ic_trash);
            button.setTag(TRASH_BUTTON_TAG);
            button.setLayoutParams(llp);
            button.setOnClickListener(this);
            trashButton = button;
            form.addView(button);
        }
    }

    /**
     * Remove the trash button if exists.
     */
    @UiThread
    void removeTrashButton() {
        if (trashButton != null) {
            form.removeView(trashButton);
            trashButton = null;
        }
    }

    /**
     * Add the trash button if does not exist.
     */
    @UiThread
    void addPlusButton() {
        if (addButton == null) {
            LinearLayout.LayoutParams llp =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            AnimatedButton button = AnimatedButton_.build(getActivity());
            button.setTag(ADD_BUTTON_TAG);
            button.setHint(getString(R.string.add_candidate));
            button.setLayoutParams(llp);
            button.setOnClickListener(this);
            addButton = button;
            form.addView(button);
        }
    }

    /**
     * Remove the plus button if exists.
     */
    @UiThread
    void removePlusButton() {
        if (addButton != null) {
            form.removeView(addButton);
            addButton = null;
        }
    }

    /**
     * Validate the current form.
     *
     * @return true if the form is valid, otherwise false.
     */
    @Override
    public boolean validate() {

        return true;

    }

    public void initData() {
        labelNb = Integer.parseInt(sLabels.getSelectedItem().toString());
        JMadapter.clear();
        Label label = null;
        for (int i = 0; i< labelNb; i++) {
            label = new Label();
            label.setNumber(i+1);
            label.setName(labels[i]);
            JMadapter.add(label);
        }

        lvLabels.setAdapter(JMadapter);
    }

    /**
     * Set data to the parent model.
     */
    @Override
    public void setData() {

        SCMajorityJudgment data = new SCMajorityJudgment();
        for (int i = 0; i<JMadapter.getItems().size(); i++) {
            data.add(JMadapter.getItem(i).getName());
        }
        parent.getSocialChoice().setData(data);
        parent.getSocialChoice().setCandidats(adapter.getItems());

        Log.d(TAG, "Social Choice updated: " + parent.getSocialChoice());
    }
}