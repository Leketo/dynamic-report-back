package com.leketo.dynamicreportback.sales.model.salesByCustomer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubSalesByCustomer {
    public String name;
    public String subTotal;
    public List<SubSalesByCustomer> subRows;

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

    public List<SubSalesByCustomer> getSubRows() {
        return subRows;
    }

    public void setSubRows(List<SubSalesByCustomer> subRows) {
        this.subRows = subRows;
    }
}
