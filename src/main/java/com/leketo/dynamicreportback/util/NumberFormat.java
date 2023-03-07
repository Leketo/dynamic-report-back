package com.leketo.dynamicreportback.util;

/*import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;*/

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.math.BigDecimal;
import java.util.Locale;

public class NumberFormat {

/*    public static String numberToStringFormat(BigDecimal amount){
        amount = amount == null ? BigDecimal.ZERO : amount;
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(
                new Locale("es-PY"),
                RuleBasedNumberFormat.SPELLOUT);
       return ruleBasedNumberFormat.format(amount);
    }*/

    public static String separarMiles(BigDecimal amount){
        amount = amount == null ? BigDecimal.ZERO : amount;
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        return formatea.format(amount).replace(",",".");

    }

}
