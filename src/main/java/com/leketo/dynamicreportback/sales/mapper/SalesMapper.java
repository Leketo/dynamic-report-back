package com.leketo.dynamicreportback.sales.mapper;


import com.leketo.dynamicreportback.sales.model.Sales;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesMapper implements RowMapper<Sales> {

    @Override
    public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
          return Sales.builder()
                .cInvoiceID(rs.getLong("c_invoice_id"))
                .sucursal(rs.getString("sucursal"))
                .cliente(rs.getString("cliente"))
                .ruc(rs.getString("ruc"))
                .objetivoDeCompra(rs.getBigDecimal("objetivo_de_compra"))
                .vendedor(rs.getString("vendedor"))
                .objetivoDelVendedor(rs.getBigDecimal("objetivo_del_vendedor") == null ? BigDecimal.ZERO : rs.getBigDecimal("objetivo_del_vendedor"))
                .nroFactura(rs.getString("nro_factura"))
                .fechaFactura(rs.getDate("fecha_factura"))
                .idPedidoPor(rs.getString("id_pedido_por"))
                .pedidoPor(rs.getString("pedido_por"))
                .facturadoPor(rs.getString("facturado_por"))
                .listaPrecio(rs.getString("lista_precio"))
                .condicionDeVenta(rs.getString("condicion_de_venta"))
                .pagado(rs.getString("pagado"))
                .cantidad(rs.getInt("cantidad"))
                .precioUnitario(rs.getBigDecimal("precio_unitario"))
                .subTotal(rs.getBigDecimal("subtotal"))
                .precioCosto(rs.getBigDecimal("precio_costo"))
                .codigo(rs.getString("codigo"))
                .producto(rs.getString("producto"))
                .familia(rs.getString("familia"))
                .subFamilia(rs.getString("subfamilia"))
                .build();
    }
}
