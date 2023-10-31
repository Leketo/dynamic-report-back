package com.leketo.dynamicreportback.sales.model.salesByCustomer;

public class SalesTargetByClient {
    public String name;
    public String subTotal;
    public String targetClient;
    public String percent;

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

    public String getTargetClient() {
        return targetClient;
    }

    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
