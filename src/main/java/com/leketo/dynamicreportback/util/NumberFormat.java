package com.leketo.dynamicreportback.util;

/*import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;*/

import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.model.Sales;
import com.leketo.dynamicreportback.sales.model.SubRow;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SalesByCustomer;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SubSalesByCustomer;
import com.leketo.dynamicreportback.sales.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
