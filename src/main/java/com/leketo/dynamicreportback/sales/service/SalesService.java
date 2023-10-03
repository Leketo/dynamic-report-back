package com.leketo.dynamicreportback.sales.service;

import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.model.Sales;
import com.leketo.dynamicreportback.sales.model.SubRow;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SalesByCustomer;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SubSalesByCustomer;
import com.leketo.dynamicreportback.sales.repository.SalesRepository;
import com.leketo.dynamicreportback.util.NumberFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SalesService {

        @Autowired
        private SalesRepository repository;

        public List<Root> salesBySellerAndFamilySubfamily(Date from, Date to){
            List<Sales> sales = repository.findByDate(from, to);
            List<Root> roots  = new ArrayList<>();
            Map<String, List<Sales>> salesPerVendedor = sales.stream()
                    .collect(Collectors.groupingBy(Sales::getVendedor));
            //VENDEDOR
            for (Map.Entry<String, List<Sales>> sellers : salesPerVendedor.entrySet()) {
                Root root = new Root();

                List<SubRow> firstRows = new ArrayList<>();

                List<Sales> salesBySeller = sellers.getValue();
                root.setName(sellers.getKey());

                List<BigDecimal> subTotals = salesBySeller.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                BigDecimal subTotal = subTotals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                root.setSubTotal(NumberFormat.separarMiles(subTotal));

                BigDecimal target = repository.getObjetivoVendedor(sellers.getKey());
                root.setTarget(NumberFormat.separarMiles(target));

                System.out.println("target:::" + target);
                System.out.println("subTotal:::" + subTotal);
                System.out.println("---");

                 BigDecimal percentSubFamilia = target.compareTo(BigDecimal.ZERO) > 0 ?
                                                subTotal.multiply(new BigDecimal(100)).divide(target,  2, RoundingMode.HALF_UP) :
                         BigDecimal.ZERO;

                 root.setPercent(NumberFormat.separarMiles(percentSubFamilia.setScale(0, RoundingMode.HALF_UP)) + " %");
                 Map<String, List<Sales>> family = salesBySeller.stream()
                        .collect(Collectors.groupingBy(Sales::getFamilia));

                //FAMILIA
                for (Map.Entry<String, List<Sales>> families : family.entrySet()) {

                    List<SubRow> secondRows = new ArrayList<>();
                    SubRow subRowAux = new SubRow();

                    List<Sales> familes = families.getValue();
                    subRowAux.setName(families.getKey());

                    List<BigDecimal> targetsFamilia = familes.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                    BigDecimal targetFamilia = targetsFamilia.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    subRowAux.setSubTotal(NumberFormat.separarMiles(targetFamilia));

                    Map<String, List<Sales>> subfamily = familes.stream()
                            .collect(Collectors.groupingBy(Sales::getSubFamilia));

                    //SUB-FAMILIA
                    for (Map.Entry<String, List<Sales>> subFa : subfamily.entrySet()) {
                        SubRow subFamilyRow = new SubRow();
                        subFamilyRow.setName(subFa.getKey());
                        List<Sales> subFamilia = subFa.getValue();
                        List<BigDecimal> subTotalsSubFamilia = subFamilia.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                        BigDecimal subTotalSubFamilia = subTotalsSubFamilia.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

                        subFamilyRow.setSubTotal(NumberFormat.separarMiles(subTotalSubFamilia));
                        secondRows.add(subFamilyRow);

                    }
                    subRowAux.setSubRows(secondRows);
                    firstRows.add(subRowAux);
                    root.setSubRows(firstRows);
                }
                roots.add(root);
            }
            return roots;
        }

        public List<SalesByCustomer> salesBySellerAndCustomer(Date from, Date to){
            List<Sales> sales = repository.findByDate(from, to);
            List<SalesByCustomer> roots  = new ArrayList<>();
            Map<String, List<Sales>> salesPerVendedor = sales.stream()
                    .collect(Collectors.groupingBy(Sales::getVendedor));

            for (Map.Entry<String, List<Sales>> sellers : salesPerVendedor.entrySet()) {

                SalesByCustomer salesByCustomer = new SalesByCustomer();
                List<SubSalesByCustomer> firstRows = new ArrayList<>();

                List<Sales> salesBySeller = sellers.getValue();
                salesByCustomer.setName(sellers.getKey());

                List<BigDecimal> subTotals = salesBySeller.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                BigDecimal subTotal = subTotals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                salesByCustomer.setSubTotal(NumberFormat.separarMiles(subTotal));

                Map<String, List<Sales>> product = salesBySeller.stream()
                        .collect(Collectors.groupingBy(Sales::getCliente));

                //PROVEEDOR
                for (Map.Entry<String, List<Sales>> products : product.entrySet()) {

                    List<SubSalesByCustomer> secondRows = new ArrayList<>();
                    SubSalesByCustomer subRowAux = new SubSalesByCustomer();

                    List<Sales> productos = products.getValue();
                    subRowAux.setName(products.getKey());

                    List<BigDecimal> targetsProducto = productos.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                    BigDecimal targetProducto = targetsProducto.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    subRowAux.setSubTotal(NumberFormat.separarMiles(targetProducto));

                    subRowAux.setSubRows(secondRows);
                    firstRows.add(subRowAux);
                    salesByCustomer.setSubRows(firstRows);

                }
                roots.add(salesByCustomer);
            }
            return roots;
        }

        public List<SalesByCustomer> salesByFamilyAndCustomer(Date from, Date to){
            List<Sales> sales = repository.findByDate(from, to);
            List<SalesByCustomer> roots  = new ArrayList<>();
            Map<String, List<Sales>> salesByFamily = sales.stream()
                    .collect(Collectors.groupingBy(Sales::getFamilia));

            for (Map.Entry<String, List<Sales>> sellers : salesByFamily.entrySet()) {

                SalesByCustomer salesByCustomer = new SalesByCustomer();
                List<SubSalesByCustomer> firstRows = new ArrayList<>();

                List<Sales> salesBySeller = sellers.getValue();
                salesByCustomer.setName(sellers.getKey());

                List<BigDecimal> subTotals = salesBySeller.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                BigDecimal subTotal = subTotals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                salesByCustomer.setSubTotal(NumberFormat.separarMiles(subTotal));

                Map<String, List<Sales>> product = salesBySeller.stream()
                        .collect(Collectors.groupingBy(Sales::getCliente));

                //PROVEEDOR
                for (Map.Entry<String, List<Sales>> products : product.entrySet()) {

                    List<SubSalesByCustomer> secondRows = new ArrayList<>();
                    SubSalesByCustomer subRowAux = new SubSalesByCustomer();

                    List<Sales> productos = products.getValue();
                    subRowAux.setName(products.getKey());

                    List<BigDecimal> targetsProducto = productos.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                    BigDecimal targetProducto = targetsProducto.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    subRowAux.setSubTotal(NumberFormat.separarMiles(targetProducto));

                    subRowAux.setSubRows(secondRows);
                    firstRows.add(subRowAux);
                    salesByCustomer.setSubRows(firstRows);

                }
                roots.add(salesByCustomer);
            }
            return roots;
        }
    }