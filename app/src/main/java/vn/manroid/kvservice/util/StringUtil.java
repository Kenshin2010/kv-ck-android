package vn.manroid.kvservice.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by manro on 12/12/2017.
 */

public class StringUtil {

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public static boolean isNullOrEmpty(String input) {
        if (input == null || "".equals(input.trim()) || "null".equals(input)) {
            return true;
        }
        return false;
    }

    public static String RoundingModeUp(Double input){
        df2.setRoundingMode(RoundingMode.UP);
        return df2.format(input);
    }

}
