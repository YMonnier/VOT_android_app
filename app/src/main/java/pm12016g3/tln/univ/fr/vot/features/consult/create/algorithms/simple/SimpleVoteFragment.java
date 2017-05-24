package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;
import pm12016g3.tln.univ.fr.vot.features.shared.AnimatedButton;
import pm12016g3.tln.univ.fr.vot.features.shared.AnimatedButton_;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
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
public class SimpleVoteFragment extends Fragment
        implements View.OnClickListener {
    private static final String TAG = SimpleVoteFragment.class.getSimpleName();
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

    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    @AfterViews
    void init() {
        Log.d(TAG, "Init");
        parent = (CreateFragment) getParentFragment();
        listView.setAdapter(adapter);
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
    @Override
    public void onClick(View view) {
        AnimatedButton button = (AnimatedButton) view;
        if (button != null) {
            if (button.getTag().equals(ADD_BUTTON_TAG)) {
                attempt();
            } else if (button.getTag().equals(TRASH_BUTTON_TAG)) {
                for (BasicItem item : adapter.getItems()) {
                    if (item.isSelected()) {
                        Log.d(TAG, "Delete this item... " + item);
                        adapter.getItems().remove(item);
                    }
                }
                adapter.notifyDataSetChanged();
                removeTrashButton();
            }
        }

    }

    /**
     * Check inputs data if there are valid.
     */
    private void attempt() {
        String candidat = inputCandidat.getText().toString();
        inputCandidat.setError(null);
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(candidat)) {
            focusView = inputCandidat;
            updateErrorUi(inputCandidat, getString(R.string.error_field_required));
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt, focus on the first
            // form field with an error.
            assert focusView != null;
            if (focusView != null)
                focusView.requestFocus();
        } else {
            updateList(candidat);
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
        adapter.add(new BasicItem(string));
        adapter.notifyDataSetChanged();
        inputCandidat.setText(null);
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
}
