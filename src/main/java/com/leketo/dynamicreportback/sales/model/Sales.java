package com.leketo.dynamicreportback.sales.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Sales {
    private Long cInvoiceID;
    private String sucursal;
    private String cliente;
    private String ruc;
    private BigDecimal objetivoDeCompra;
    private BigDecimal objetivo;
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

    public Long getcInvoiceID() {
        return cInvoiceID;
    }

    public void setcInvoiceID(Long cInvoiceID) {
        this.cInvoiceID = cInvoiceID;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public BigDecimal getObjetivoDeCompra() {
        return objetivoDeCompra;
    }

    public void setObjetivoDeCompra(BigDecimal objetivoDeCompra) {
        this.objetivoDeCompra = objetivoDeCompra;
    }

    public String getVendedor() {
        return vendedor;
    }

    public BigDecimal getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(BigDecimal objetivo) {
        this.objetivo = objetivo;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getObjetivoDelVendedor() {
        return objetivoDelVendedor;
    }

    public void setObjetivoDelVendedor(BigDecimal objetivoDelVendedor) {
        this.objetivoDelVendedor = objetivoDelVendedor;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getIdPedidoPor() {
        return idPedidoPor;
    }

    public void setIdPedidoPor(String idPedidoPor) {
        this.idPedidoPor = idPedidoPor;
    }

    public String getPedidoPor() {
        return pedidoPor;
    }

    public void setPedidoPor(String pedidoPor) {
        this.pedidoPor = pedidoPor;
    }

    public String getFacturadoPor() {
        return facturadoPor;
    }

    public void setFacturadoPor(String facturadoPor) {
        this.facturadoPor = facturadoPor;
    }

    public String getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(String listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public String getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(String condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(BigDecimal precioCosto) {
        this.precioCosto = precioCosto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(String subFamilia) {
        this.subFamilia = subFamilia;
    }
}
