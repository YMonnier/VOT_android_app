package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
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

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;
import pm12016g3.tln.univ.fr.vot.features.consult.create.Validable;
import pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.InvitationFragment_;
import pm12016g3.tln.univ.fr.vot.features.shared.AnimatedButton;
import pm12016g3.tln.univ.fr.vot.features.shared.AnimatedButton_;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
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

@EFragment(R.layout.consult_create_algo_simple_vote_fragment)
@OptionsMenu(R.menu.consult_create_menu_two_arrows)
public class SimpleVoteFragment extends AppFragment
        implements View.OnClickListener, Validable {
    private static final String TAG = SimpleVoteFragment.class.getSimpleName();
    private final int ADD_BUTTON_TAG = 143;
    private final int TRASH_BUTTON_TAG = 243;

    @ViewById(R.id.input_candidat)
    EditText inputCandidat;

    @ViewById(R.id.input_nb_choices)
    EditText inputNbChoice;

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById(R.id.tidy)
    Switch tidyView;

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

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        Log.d(TAG, "Init");
        fragmentTitle = getString(R.string.fragment_title_sm);
        parent = (CreateFragment) getParentFragment();
        listView.setAdapter(adapter);
    }

    /**
     * Go to the Invitation view.
     *
     * Check if all data is available and valid.
     * If it is not that case, the App should dsplay a message error.
     */
    @OptionsItem(R.id.menu_item_next_arrow)
    void next() {
        Log.d(TAG, "Next button");
        if (validate()) {
            if (checkListNumberOfChoices()) {
                setData();
                parent.nextStep(this, new InvitationFragment_());
            } else
                Snack.showFailureMessage(getView(),
                        getString(R.string.snack_error_no_algo_selected),
                        Snackbar.LENGTH_LONG);
        }
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
        item.setSelected(!item.isSelected());
        adapter.notifyDataSetChanged();
        if (shouldShowTrashButton()) {
            if (trashButton == null)
                addTrashButton();
        } else {
            removeTrashButton();
        }
    }

    /**
     * Handle when user does a long tap on the list view.
     * Delete the item selected.
     *
     * @param item item selected.
     */
    @ItemLongClick(R.id.listView)
    void listViewOnLongClick(BasicItem item) {
        adapter.getItems().remove(item);
        adapter.notifyDataSetChanged();
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
                if (validate()) {
                    String candidat = inputCandidat.getText().toString();
                    updateList(candidat);
                }
            } else if (button.getTag().equals(TRASH_BUTTON_TAG)) {

                adapter.getItems()
                        .removeAll(
                                Stream.of(adapter.getItems())
                                        .filter(BasicItem::isSelected)
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
        List<BasicItem> list = adapter.getItems();
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
        BasicItem item = new BasicItem(string);
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
        inputNbChoice.setError(null);
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
        String nbChoice = inputNbChoice.getText().toString();
        resetErrorUi();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(nbChoice)) {
            if (focusView == null)
                focusView = inputNbChoice;
            updateErrorUi(inputNbChoice, getString(R.string.error_field_required));
            cancel = true;
        }
        Log.d(TAG, "Add On List View ? " + cancel);
        if (cancel) {
            // There was an error; don't attempt, focus on the first
            // form field with an error.
            assert focusView != null;
            if (focusView != null)
                focusView.requestFocus();
        }

        return !cancel;
    }

    /**
     * Check if the list size is lower
     * than the number of choice.
     *
     * @return true if the size of lise is lower than the
     * number of choice otherwise false.
     */
    private boolean checkListNumberOfChoices() {
        int nbChoice = Integer.parseInt(inputNbChoice.getText().toString());
        Log.d(TAG, "nbChoice: " + nbChoice + " listSize: " + adapter.getItems().size());
        return adapter.getItems().size() > nbChoice;
    }

    /**
     * Set data to the parent model.
     */
    @Override
    public void setData() {
        int nbChoice = Integer.parseInt(inputNbChoice.getText().toString());
        boolean tidy = tidyView.isChecked();
        SCSMajorityBallot data = new SCSMajorityBallot(tidy, nbChoice);
        parent.getSocialChoice().setData(data);
    }
}
