package com.leketo.dynamicreportback.sales.model.salesByCustomer;

import com.leketo.dynamicreportback.sales.model.SubSalesByFamilyBySubFamily;
import lombok.Setter;

import java.util.List;
public class SalesByFamilyBySubFamily {

    public int id;
    public String name;
    public String subTotal;
    public String costoTotal;
    public String cantidad;
    public String diference;
    public String percent;
    public List<SubSalesByFamilyBySubFamily> subRows;

    public String getDiference() {
        return diference;
    }

    public void setDiference(String diference) {
        this.diference = diference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(String costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public List<SubSalesByFamilyBySubFamily> getSubRows() {
        return subRows;
    }

    public void setSubRows(List<SubSalesByFamilyBySubFamily> subRows) {
        this.subRows = subRows;
    }
}
