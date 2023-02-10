package com.leketo.dynamicreportback.sales.repository;

import com.leketo.dynamicreportback.sales.mapper.SalesMapper;
import com.leketo.dynamicreportback.sales.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesRepository {

    private final String SQL_VENTA = "select s.c_invoice_id, p.name as sucursal, r.name as cliente, r.value as ruc, round(r.salestarget) as objetivo_de_compra, u.name as vendedor, a.salesobjective as objetivo_del_vendedor, s.nrofacturaimprimir as nro_factura, s.dateinvoiced as fecha_factura, o.salesrep_id as id_pedido_por, n.name as pedido_por, t.name as facturado_por, v.name as lista_precio, s.paymentrule as condicion_de_venta, s.ispaid as pagado, round (w.qtyentered) as cantidad, round (w.priceentered) as precio_unitario, round (w.linenetamt) as subtotal, round (m.pricelist) as precio_costo, x.value as codigo, x.name as producto, y.name as familia, z.name as subfamilia from c_invoice s inner join ad_org p on p.ad_org_id = s.ad_org_id inner join c_bpartner r on r.c_bpartner_id = s.c_bpartner_id inner join ad_user t on s.createdby = t.ad_user_id inner join ad_user u on s.salesrep_id = u.ad_user_id inner join c_order o on s.c_order_id = o.c_order_id inner join ad_user n on o.createdby = n.ad_user_id inner join m_pricelist v on v.m_pricelist_id = s.m_pricelist_id inner join c_invoiceline w on s.c_invoice_id = w.c_invoice_id inner join m_product x on w.m_product_id = x.m_product_id inner join m_product_family y on x.m_product_family_id = y.m_product_family_id inner join m_product_subfamily z on x.m_product_subfamily_id = z.m_product_subfamily_id inner join m_productprice m on m.m_product_id = w.m_product_id inner join c_bpartner a on u.c_bpartner_id = a.c_bpartner_id where s.isactive = 'Y' and s.docstatus = 'CO' and s.issotrx = 'Y' and m.m_pricelist_version_id = 1010519 and s.created between '2022-12-01' and '2022-12-31' order by x.value";

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public List<Sales> getAll() {
        return jdbcTemplate.query(SQL_VENTA, new SalesMapper());
    }
}
