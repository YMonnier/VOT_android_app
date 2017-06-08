package pm12016g3.tln.univ.fr.vot.utilities.validator;

/**
 * Project VOT.
 * Package pm12016g3.tln.univ.fr.vot.utilities.validator.
 * File PasswordValidator.java.
 * Created by Ysee on 12/05/2017 - 10:50.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class PasswordValidator {
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Validate password with its length
     *
     * @param password password to dateValidate
     * @return true if valid length password, otherwise, false
     */
    public static boolean validate(final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }
}
