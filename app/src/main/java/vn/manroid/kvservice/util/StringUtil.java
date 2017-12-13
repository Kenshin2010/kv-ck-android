package vn.manroid.kvservice.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by manro on 12/12/2017.
 */

public class StringUtil {
    public static boolean isNullOrEmpty(String input) {
        if (input == null || "".equals(input.trim()) || "null".equals(input)) {
            return true;
        }
        return false;
    }


}
