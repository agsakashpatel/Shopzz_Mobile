package com.app.shopzz.utility;

import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.app.shopzz.R;

import java.util.regex.Pattern;

/**
 * This class validate all required credentials.
 * <p><strong>Example: </strong></p> string length, URL format, pattern matching
 */
public class ValidationClass {

    // variable declaration
    private static final String PLUS_SIGN = "+";
    private static final String TAG = ValidationClass.class.getSimpleName();


    /**
     * This method validate string if it is empty or not.
     * <p><strong>Note:</strong> we can use {@link TextUtils#isEmpty(CharSequence)} method directly.</p>
     * <strong>Example:</strong>
     * <pre>{@code
     * if(ValidationClass.isEmpty(edtFirstName.getText().toString().trim())){
     * // Perform action
     * }else{
     * // Perform action
     * }
     * OR
     * if(TextUtils.isEmpty(edtFirstName.getText().toString().trim())){
     * // Perform action
     * } else{
     * // Perform action
     * }
     * }</pre>
     *
     * @param inputString(String) : user input string
     * @return (boolean) : <code>true</code> if string is null or zero length;
     * @see <a href="https://developer.android.com/reference/android/text/TextUtils.html#isEmpty%28java.lang.CharSequence%29">isEmpty</a>
     */
    public static boolean isEmpty(String inputString) {
        return TextUtils.isEmpty(inputString);
    }

    /**
     * To check string is start with http or https.
     * <p><strong>Note:</strong> we can use {@link URLUtil#isNetworkUrl(String)} method directly.</p>
     * <strong>Example:</strong>
     * <pre>{@code
     * String url = http://codebase.on-linedemo.com/CommonWebService.asmx/checkAppVersion
     *
     * if(ValidationClass.isNetworkURL(url)){
     * // Perform action
     * }else{
     * // Perform action
     * }
     * OR
     * if(URLUtil.isNetworkURL(url)){
     * // Perform action
     * } else{
     * // Perform action
     * }
     * }</pre>
     *
     * @param inputString : input string that to be verified
     * @see <a href="https://developer.android.com/reference/android/webkit/URLUtil.html#isNetworkUrl(java.lang.String)"> isNetworkUrl </a>
     */
    public static boolean isNetworkURL(String inputString) {
        return !TextUtils.isEmpty(inputString) && URLUtil.isNetworkUrl(inputString);
    }


    /**
     * This method validate string with specific patterns
     * e.g. email,alphaNumericPassword,webUrl
     * <p><strong>Note:</strong> we can use {@link Pattern#matches(String, CharSequence)} method directly.</p>
     * <strong>Example:</strong>
     * <pre>{@code
     * if(ValidationClass.matchPattern(email,Patterns.EMAIL_ADDRESS.pattern())){
     * // Perform action
     * }else{
     * // Perform action
     * }
     * OR
     * if(Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)){
     * // Perform action
     * } else{
     * // Perform action
     * }
     * }</pre>
     *
     * @param inputString(String) : user input string
     * @param pattern(String)     : patterns to be matched with inputString
     * @return (boolean) : it return <code>false</code> if inputString does not match with required pattern.
     * @see Patterns#EMAIL_ADDRESS
     */
    public static boolean matchPattern(String inputString, String pattern) {
        return !(TextUtils.isEmpty(inputString) || pattern.isEmpty()) && Pattern.matches(pattern, inputString);

    }

    /**
     * This method validate the phone number with specific length
     *
     * @param inputPhoneNumber(String) : user input inputPhoneNumber e.g. 9999999999
     * @param phoneNumberLength(int)   : phone number length e.g. 10
     * @return (boolean) : it return <code>false</code> if inputPhoneNumber length is not equal to phoneNumberLength
     */
    public static boolean checkPhoneNumber(String inputPhoneNumber, int phoneNumberLength) {
        return !(TextUtils.isEmpty(inputPhoneNumber) || phoneNumberLength <= 0) && inputPhoneNumber.trim().length() == phoneNumberLength;
    }

    /**
     * This method check string with minimum length
     *
     * @param inputString(String) : input string
     * @param minLength(int)      :  minimum length e.g. 6
     * @return (boolean) : it return <code>false<code/>  if inputString is less than minLength
     */
    public static boolean checkMinLength(String inputString, int minLength) {
        return !(TextUtils.isEmpty(inputString) || minLength <= 0) && inputString.trim().length() >= minLength;
    }

    /**
     * This method check string with maximum length
     *
     * @param inputString(String) : input string
     * @param maxLength(int)      :  maximum length e.g 10
     * @return (boolean) : it return <code>false</code> if input string is greater than maxLength
     */
    public static boolean checkMaxLength(String inputString, int maxLength) {
        return !(TextUtils.isEmpty(inputString) || maxLength <= 0) && inputString.trim().length() <= maxLength;
    }

    /**
     * This method validate if the number is negative or not
     *
     * @return (boolean) : it return <code>true</code> if the parameter number is less than 0(zero)
     */
    private static boolean isNumberNegative(int number) {
        return number <= 0;
    }

    /**
     * This method check whether inputPhoneNumber starts with +(plus sign) or not.
     *
     * @param inputPhoneNumber(String) : user input inputPhoneNumber
     * @return (boolean) : it return <code>false</code> if and only if inputPhoneNumber does not start with +(plus sign)
     */
    public static boolean checkPlusSign(String inputPhoneNumber) {
        return inputPhoneNumber.trim().startsWith(PLUS_SIGN);
    }

    /**
     * This logic use to check email or phone number in single EditText.
     *
     * @param inputString (String) : user input String
     */
    public boolean validateEmailOrPhone(String inputString) {
        if (inputString.isEmpty()) {
            return false;
        } else {
            if (!TextUtils.isDigitsOnly(inputString)) {
                // show number related error message
                return false;
            } else {
                return matchPattern(inputString, Patterns.EMAIL_ADDRESS.pattern());
            }
        }
    }

    public static void getRatingCount(double count, ImageView ivRatingOne, ImageView ivRatingTwo, ImageView ivRatingThree, ImageView ivRatingFour,
                                      ImageView ivRatingFive) {
        if (count > 0 && count <= 1) {
            ivRatingOne.setImageResource(R.mipmap.rating_star_rated);
            ivRatingTwo.setImageResource(R.mipmap.rating_star_normal);
            ivRatingThree.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFour.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFive.setImageResource(R.mipmap.rating_star_normal);
        } else if (count > 1 && count <= 2) {
            ivRatingOne.setImageResource(R.mipmap.rating_star_rated);
            ivRatingTwo.setImageResource(R.mipmap.rating_star_rated);
            ivRatingThree.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFour.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFive.setImageResource(R.mipmap.rating_star_normal);
        } else if (count > 2 && count <= 3) {
            ivRatingOne.setImageResource(R.mipmap.rating_star_rated);
            ivRatingTwo.setImageResource(R.mipmap.rating_star_rated);
            ivRatingThree.setImageResource(R.mipmap.rating_star_rated);
            ivRatingFour.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFive.setImageResource(R.mipmap.rating_star_normal);
        } else if (count > 3 && count <= 4) {
            ivRatingOne.setImageResource(R.mipmap.rating_star_rated);
            ivRatingTwo.setImageResource(R.mipmap.rating_star_rated);
            ivRatingThree.setImageResource(R.mipmap.rating_star_rated);
            ivRatingFour.setImageResource(R.mipmap.rating_star_rated);
            ivRatingFive.setImageResource(R.mipmap.rating_star_normal);
        } else if (count > 4 && count <= 5) {
            ivRatingOne.setImageResource(R.mipmap.rating_star_rated);
            ivRatingTwo.setImageResource(R.mipmap.rating_star_rated);
            ivRatingThree.setImageResource(R.mipmap.rating_star_rated);
            ivRatingFour.setImageResource(R.mipmap.rating_star_rated);
            ivRatingFive.setImageResource(R.mipmap.rating_star_rated);
        } else {
            ivRatingOne.setImageResource(R.mipmap.rating_star_normal);
            ivRatingTwo.setImageResource(R.mipmap.rating_star_normal);
            ivRatingThree.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFour.setImageResource(R.mipmap.rating_star_normal);
            ivRatingFive.setImageResource(R.mipmap.rating_star_normal);
        }
    }
}