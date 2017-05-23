package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.CreateFragment;

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
public class SimpleVoteFragment extends Fragment {
    private static final String TAG = SimpleVoteFragment.class.getSimpleName();

    @ViewById(R.id.input_candidat)
    EditText inputCandidat;

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById(R.id.form)
    LinearLayout form;

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
     * check the input candidat id its is conform.
     *
     * @param textView action source
     * @param actionId type of action(IME_ID)
     * @param keyEvent event
     */
    @EditorAction({R.id.input_candidat})
    void onNextActionsEditText(TextView textView, int actionId, KeyEvent keyEvent) {
        /*Log.d(TAG, "Action test..");
        if (actionId == R.id.candidat_vote_done || actionId == EditorInfo.IME_ACTION_DONE) {
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
                Log.d(TAG, "Add candidat... " + candidat);
                adapter.add(new BasicItem(candidat));
                adapter.notifyDataSetChanged();
                inputCandidat.setText(null);
            }
        }
        return false;*/
    }

    @TextChange(R.id.input_candidat)
    void onTextChangesOnHelloTextView(CharSequence text, TextView hello, int before, int start, int count) {
        Log.d(TAG, String.valueOf(text));
        if (text.length() > 0 && form.getChildCount() == 1) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            AddButton button = AddButton_.build(getActivity());
            button.setHint(getString(R.string.add_candidate));
            button.setLayoutParams(llp);
            form.addView(button);
        } else if (text.length() == 0) {
            form.removeViewAt(1);
        }
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
}
