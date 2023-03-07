package com.leketo.dynamicreportback.sales.model;

import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
public class SubRow{
    public String name;
    public String subTotal;
    public String target;
    public List<SubRow> subRows;
}
