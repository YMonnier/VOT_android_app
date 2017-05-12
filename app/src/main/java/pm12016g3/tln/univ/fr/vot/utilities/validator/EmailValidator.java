package pm12016g3.tln.univ.fr.vot.utilities.validator;

import java.util.regex.Pattern;

/**
 * Project VOT.
 * Package pm12016g3.tln.univ.fr.vot.utilities.validator.
 * File EmailValidator.java.
 * Created by Ysee on 12/05/2017 - 10:03.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class EmailValidator {
    /**
     * Regular expression to test an email.
     */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate email with regular expression
     *
     * @param email email for validation
     * @return true valid email, otherwise, false
     */
    public static boolean validate(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
}
