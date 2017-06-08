package pm12016g3.tln.univ.fr.vot.features.consult.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.jm.JMFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.SimpleVoteFragment_;
import pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.stv.STVFragment_;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.DateValidator;
import pm12016g3.tln.univ.fr.vot.utilities.views.Snack;
import pm12016g3.tln.univ.fr.vot.utilities.views.ViewUtils;
import pm12016g3.tln.univ.fr.vot.utilities.views.fragment.AppFragment;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.
 * File SettingsFragment.java.
 * Created by Ysee on 19/05/2017 - 17:50.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EFragment(R.layout.consult_create_settings_fragment)
@OptionsMenu(R.menu.consult_create_menu_next_arrow)
public class SettingsFragment extends AppFragment
        implements Validable,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    private static final String TAG = SettingsFragment.class.getSimpleName();

    /**
     * Default spinner value
     * That attribute should not be selected.
     */
    private static final String DEFAULT_ALGORITHM = "Type";

    @ViewById(R.id.et_calendar)
    EditText etCalendar;

    /**
     * Confidentiality boolean.
     * The user can swift on/off
     * the confidentiality parameter.
     */
    @ViewById(R.id.confidentiality)
    Switch confidentiality;

    /**
     * List of algorithms
     */
    @ViewById(R.id.algorithms)
    Spinner algorithms;

    /**
     * Social Choice Title.
     */
    @ViewById(R.id.input_title)
    EditText title;

    /**
     * Optional Social Choice description
     */
    @ViewById(R.id.input_desc)
    EditText description;


    /**
     * Parent fragment.
     * This variable is used to send and
     * to receive data from the parent.
     */
    CreateFragment parent;

    /**
     * Date selected
     */
    Calendar calendar;

    @AfterViews
    void init() {
        fragmentTitle = getString(R.string.fragment_title_settings);
        parent = (CreateFragment) getParentFragment();
        algorithms.setOnTouchListener(spinnerOnTouchHandler);
    }

    /**
     * Display date picker when touch startDateField
     */
    @Click(R.id.et_calendar)
    void onClickOnSelectStartDate() {
        Calendar calendar = Calendar.getInstance();

        // Hide keyboard before to show DatePickerDialog
        ViewUtils.closeKeyboard(getActivity(),
                getActivity().getCurrentFocus());

        // Setup date picker with a minimum date.
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), 0, this, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        pickerDialog.show();
    }

    @OptionsItem(R.id.menu_item_next_arrow)
    void next() {
        Log.d(TAG, "Next button");
        if (validate()) {
            setData(); // Set current data to parent.socialChoice.
            ViewUtils.closeKeyboard(getActivity(),
                    getActivity().getCurrentFocus());

            boolean newFragment = false;
            SocialChoice.Type type = parent.getSocialChoice().getType();
            SocialChoice.Type typeAlgoView = parent.algorithmTypeFragment();
            if (typeAlgoView != null) {
                if (type != typeAlgoView) {
                    newFragment = true;
                    // Delete old fragment.
                    for (int i = 1; i < parent.getFragments().size(); i++) {
                        parent.getFragments().remove(i);
                    }
                }
            } else
                newFragment = true;


            if (newFragment) {
                // Insert new fragment
                switch (type) {
                    case SM:
                        parent.nextStep(this, new SimpleVoteFragment_());
                        break;
                    case STV:
                        parent.nextStep(this, new STVFragment_());
                        break;
                    case JM:
                        parent.nextStep(this, new JMFragment_());
                        break;
                }
            } else // Go the existing next view.
                parent.nextStep();

        }
    }

    /**
     * Check all input data when user
     * press on next button edit text.
     *
     * @param textView action source
     * @param actionId type of action(IME_ID)
     * @param keyEvent event
     */
    @EditorAction({R.id.input_title})
    void onNextActionsEditText(TextView textView,
                               int actionId,
                               KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            validate();
        }
    }

    @Override
    public boolean validate() {
        updateResetErrorUi();

        boolean cancel = false;
        boolean isSpinner = false;
        View focusView = null;

        Object selectedItem = algorithms.getSelectedItem();
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
        String date = this.etCalendar.getText().toString();

        assert selectedItem != null;

        if (TextUtils.isEmpty(date)) {
            updateErrorUi(this.etCalendar, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = this.etCalendar;
            cancel = true;
        } else if (TextUtils.isEmpty(title)) {
            updateErrorUi(this.title, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = this.title;
            cancel = true;
        } else if (TextUtils.isEmpty(description)) {
            updateErrorUi(this.description, getString(R.string.error_field_required));
            if (focusView == null)
                focusView = this.description;
            cancel = true;
        } else if (selectedItem != null) {
            String algorithm = selectedItem.toString();
            Log.d(TAG, algorithm);
            if (algorithm.equals(DEFAULT_ALGORITHM)) {
                cancel = true;
                if (focusView == null) {
                    focusView = algorithms;
                    isSpinner = true;
                }
            }
        }

        Log.d(TAG, String.valueOf(cancel));
        if (cancel) {
            // There was an error; don't attempt, focus on the first
            // form field with an error.
            assert focusView != null;
            if (focusView != null)
                focusView.requestFocus();
        }
        if (isSpinner)
            Snack.showFailureMessage(getView(),
                    getString(R.string.snack_error_no_algo_selected),
                    Snackbar.LENGTH_LONG);

        return !cancel;
    }

    @Override
    public void setData() {
        String typeStr = (String) algorithms.getSelectedItem();
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();

        SocialChoice.Type type = null;
        if (typeStr.equals(getString(R.string.algo_ms))) {
            type = SocialChoice.Type.SM;
        } else if (typeStr.equals(getString(R.string.algo_ky))) {
            type = SocialChoice.Type.KY;
        } else if (typeStr.equals(getString(R.string.algo_jm))) {
            type = SocialChoice.Type.JM;
        } else if (typeStr.equals(getString(R.string.algo_stv))) {
            type = SocialChoice.Type.STV;
        }

        parent.getSocialChoice().setTitle(title);
        parent.getSocialChoice().setDescription(description);
        parent.getSocialChoice().setConfidentiality(confidentiality.isChecked());
        parent.getSocialChoice().setEndDate(String.valueOf(calendar.getTimeInMillis()));
        if (type != null)
            parent.getSocialChoice().setType(type);
    }

    /**
     * Reset all error input views.
     */
    @UiThread
    void updateResetErrorUi() {
        // Reset errors.
        title.setError(null);
        description.setError(null);
        confidentiality.setError(null);
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

    @UiThread
    void updateErrorUi(final Switch view, final String error) {
        view.setError(error);
    }

    /**
     * Update clickable button depending on a status
     *
     * @param status true if we want to
     *               disable all clickable buttons, otherwise, false
     */
    @UiThread
    void updateLockUi(boolean status) {
        title.setEnabled(!status);
        description.setEnabled(!status);
        algorithms.setEnabled(!status);
    }

    /**
     * Handler when user
     * clicks on input description to hide
     * keyboard if necessary.
     */
    @Click(R.id.input_desc)
    void clickOnInputDescription() {
        Log.d(TAG, "clickOnInputDescription");
        ViewUtils.closeKeyboard(getActivity(),
                getActivity().getCurrentFocus());
    }

    @Click(R.id.algorithms_help)
    void algorithmsHelper() {
        ViewUtils.closeKeyboard(getActivity(),
                getActivity().getCurrentFocus());
        TutorialActivity_.intent(getActivity()).start();
        Log.d(TAG, "OnClick - Algorithm Helper....");
    }

    @Click(R.id.confidentiality_help)
    void confidentialityHelper() {
        ViewUtils.closeKeyboard(getActivity(),
                getActivity().getCurrentFocus());
        ConfidentialityActivity_.intent(getActivity()).start();
        Log.d(TAG, "OnClick - Confidentiality Helper....");
    }

    /**
     * View OnTouchListener for the current Algorithm Spinner.
     * Allowing to hide the ketboard when user press on the spinner.
     */
    private View.OnTouchListener spinnerOnTouchHandler = (v, event) -> {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "On Touch On Spinner");
            ViewUtils.closeKeyboard(getActivity(),
                    getActivity().getCurrentFocus());
        }
        return false; // Display the spinner
    };

    /**
     * Date setter
     *
     * @param view  date setter
     * @param year  year
     * @param month mount
     * @param day   day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        ;
        calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        if (DateValidator.dateValidate(calendar)) {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    0, this, hour, minute, false);
            timePickerDialog.show();
        } else {
            etCalendar.setText("");
            updateErrorUi(etCalendar, getString(R.string.date_picker_error_invalid));
        }
    }

    /**
     * Time setter
     *
     * @param timePicker time picker view
     * @param i          hour of day
     * @param i1         minute
     */
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        // i = hourOfDay
        // i1 = minute
        final String FORMAT = "yyyy-MM-dd ";
        calendar.set(Calendar.HOUR_OF_DAY, i);
        calendar.set(Calendar.MINUTE, i1);

        if (DateValidator.timeValidate(calendar)) {
            etCalendar.setText(getDateFormatIso8601(calendar.getTime()));
        } else {
            etCalendar.setText("");
            updateErrorUi(etCalendar, getString(R.string.time_picker_error_invalid));
        }
    }

    /**
     * Convert date to timestamp ISO 8601
     *
     * @param date date to convert.
     * @return String Timestamp ISO 8601 date
     */
    public static String getDateFormatIso8601(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRANCE);
        return simpleDateFormat.format(date);
    }
}
