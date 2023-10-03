package com.leketo.dynamicreportback.sales.model;

import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
public class SubRow{
    public String name;
    public String subTotal;
    public String target;
    public String percent;
    public List<SubRow> subRows;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public List<SubRow> getSubRows() {
        return subRows;
    }

    public void setSubRows(List<SubRow> subRows) {
        this.subRows = subRows;
    }
}
