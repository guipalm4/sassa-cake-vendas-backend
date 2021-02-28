package com.sassacakes.sales.core.util;

import java.text.NumberFormat;

public class Currencies {

    public static String format(Number number) {
        if (number == null) {
            return null;
        }

        return NumberFormat.getCurrencyInstance(Constants.BRAZIL_LOCALE).format(number);
    }

}
