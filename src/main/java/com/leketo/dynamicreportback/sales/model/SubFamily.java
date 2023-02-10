package com.leketo.dynamicreportback.sales.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubFamily {
    private String name;
    private BigDecimal subTotal;
    private BigDecimal target;
}
