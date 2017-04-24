package cc.lkme.databindingsample;

import android.content.res.Resources;
import android.databinding.InverseMethod;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by LinkedME06 on 19/04/2017.
 */

public class Conv {
    @InverseMethod("toPhoneNumberType")
    public static int toInt(PhoneNumberType phoneNumberType) {
        return phoneNumberType.ordinal();
    }

    public static PhoneNumberType toPhoneNumberType(int ordinal) {
        return PhoneNumberType.values()[ordinal];
    }

    @InverseMethod("toDouble")
    public static String toString(TextView view, double oldValue,
                                  double value) {
        NumberFormat numberFormat = getNumberFormat(view);
        try {
            // Don't return a different value if the parsed value
            // doesn't change
            String inView = view.getText().toString();
            double parsed =
                    numberFormat.parse(inView).doubleValue();
            if (parsed == value) {
                return view.getText().toString();
            }
        } catch (ParseException e) {
            // Old number was broken
        }
        return numberFormat.format(value);
    }

    public static double toDouble(TextView view, double oldValue,
                                  String value) {
        NumberFormat numberFormat = getNumberFormat(view);
        try {
            return numberFormat.parse(value).doubleValue();
        } catch (ParseException e) {
            Resources resources = view.getResources();
            String errStr = resources.getString(R.string.badNumber);
            view.setError(errStr);
            return oldValue;
        }
    }

    private static NumberFormat getNumberFormat(View view) {
        Resources resources= view.getResources();
        Locale locale = resources.getConfiguration().locale;
        NumberFormat format =
                NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) format;
            decimalFormat.setGroupingUsed(false);
        }
        return format;
    }

}
