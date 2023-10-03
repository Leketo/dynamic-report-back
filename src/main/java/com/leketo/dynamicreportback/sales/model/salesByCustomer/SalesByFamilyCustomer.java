package com.leketo.dynamicreportback.sales.model.salesByCustomer;

import lombok.Setter;

import java.util.List;

@Setter
public class SalesByFamilyCustomer {
    public int id;
    public String name;
    public String subTotal;
    public List<SalesByFamilyCustomer> subRows;
}
