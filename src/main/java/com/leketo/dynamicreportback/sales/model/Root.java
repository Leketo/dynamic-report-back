package com.leketo.dynamicreportback.sales.model;

import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
public class Root{
    public int id;
    public String name;
    public BigDecimal subTotal;
    public BigDecimal target;
    public List<SubRow> subRows;
}