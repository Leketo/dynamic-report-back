package com.leketo.dynamicreportback.sales.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    private Long cInvoiceID;
    private String sucursal;
    private String cliente;
    private String ruc;
    private BigDecimal objetivoDeCompra;
    private String vendedor;
    private BigDecimal objetivoDelVendedor;
    private String nroFactura;
    private Date fechaFactura;
    private String idPedidoPor;
    private String pedidoPor;
    private String facturadoPor;
    private String listaPrecio;
    private String condicionDeVenta;
    private String pagado;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;
    private BigDecimal precioCosto;
    private String codigo;
    private String producto;
    private String familia;
    private String subFamilia;
}
