package com.leketo.dynamicreportback.sales.service;

import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.model.Sales;
import com.leketo.dynamicreportback.sales.model.SubRow;
import com.leketo.dynamicreportback.sales.model.SubSalesByFamilyBySubFamily;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SalesByCustomer;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SalesByFamilyBySubFamily;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SalesTargetByClient;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SubSalesByCustomer;
import com.leketo.dynamicreportback.sales.repository.SalesRepository;
import com.leketo.dynamicreportback.util.NumberFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
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

        public List<SalesByFamilyBySubFamily> salesByFamilyBySubFamily(Date from, Date to) {
          List<Sales> sales = repository.findByDate(from, to);
          List<SalesByFamilyBySubFamily> results = new ArrayList<>();

          Map<String, List<Sales>> salesByFamily = sales.stream()
                .collect(Collectors.groupingBy(Sales::getFamilia));

        // FAMILIA
        for (Map.Entry<String, List<Sales>> familyEntry : salesByFamily.entrySet()) {
            SalesByFamilyBySubFamily family = new SalesByFamilyBySubFamily();

            family.setName(familyEntry.getKey());

            List<Sales> familySales = familyEntry.getValue();

            BigDecimal familySubTotal = familySales.stream()
                    .map(Sales::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            family.setSubTotal(NumberFormat.separarMiles(familySubTotal));

            BigDecimal familyCostoTotal = familySales.stream()
                    .filter(s -> s.getCantidad() != null && s.getPrecioCosto() != null)
                    .map(s -> new BigDecimal(s.getCantidad()).multiply(s.getPrecioCosto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            family.setCostoTotal(NumberFormat.separarMiles(familyCostoTotal));


            family.setPercent("0" + " %");

            Map<String, List<Sales>> salesBySubFamily = familySales.stream()
                    .collect(Collectors.groupingBy(Sales::getSubFamilia));

            List<SubSalesByFamilyBySubFamily> subRows = new ArrayList<>();
            List<Integer> allSubFamilyCounts = new ArrayList<>();

            // SUB-FAMILIA
            for (Map.Entry<String, List<Sales>> subFamilyEntry : salesBySubFamily.entrySet()) {
                SubSalesByFamilyBySubFamily subFamily = new SubSalesByFamilyBySubFamily();

                subFamily.setName(subFamilyEntry.getKey());

                BigDecimal subFamilySubTotal = subFamilyEntry.getValue().stream()
                        .map(Sales::getSubTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                subFamily.setSubTotal(NumberFormat.separarMiles(subFamilySubTotal));


                // Calculando el costoTotal para la subfamilia
                BigDecimal costoTotal = subFamilyEntry.getValue().stream()
                        .filter(s -> s.getCantidad() != null && s.getPrecioCosto() != null)
                        .map(s -> new BigDecimal(s.getCantidad()).multiply(s.getPrecioCosto()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                subFamily.setCostoTotal(NumberFormat.separarMiles(costoTotal));

                Integer totalItems = subFamilyEntry.getValue().stream()
                        .mapToInt(Sales::getCantidad)
                        .sum();
                subFamily.setCantidad(NumberFormat.separarMiles(BigDecimal.valueOf(totalItems)));
                allSubFamilyCounts.add(totalItems);
                family.setPercent("0" + " %");
                subRows.add(subFamily);
            }

            subRows.sort((a, b) -> new BigDecimal(
                    b.getSubTotal().replace(".", "")
                            .replace(",", "."))
                    .compareTo(new BigDecimal(a.getSubTotal()
                            .replace(".", "")
                            .replace(",", "."))));
            family.setSubRows(subRows);

            // Calculando la cantidad total de items para la Familia
            Integer totalItemsFamily = allSubFamilyCounts.stream().mapToInt(Integer::intValue).sum();
            family.setCantidad(NumberFormat.separarMiles(BigDecimal.valueOf(totalItemsFamily)));
            family.setSubRows(subRows);
            results.add(family);
        }

            results.sort((a, b) -> new BigDecimal(
                    b.getSubTotal().replace(".", "")
                            .replace(",", "."))
                    .compareTo(new BigDecimal(a.getSubTotal()
                            .replace(".", "")
                            .replace(",", "."))));


            return results;
    }

        public List<SalesTargetByClient> salesTargetByClient(Date from, Date to) {
         List<Sales> sales = repository.findByDate(from, to);
         List<SalesTargetByClient> resultList = new ArrayList<>();

        // Agrupamos por Cliente
        Map<String, List<Sales>> salesPerCliente = sales.stream()
                .collect(Collectors.groupingBy(Sales::getCliente));

        // Iteramos sobre cada entrada en el mapa (Cliente -> Lista de Ventas)
        for (Map.Entry<String, List<Sales>> entry : salesPerCliente.entrySet()) {
            SalesTargetByClient salesTargetByClient = new SalesTargetByClient();

            // Establecemos el nombre (Cliente)
            salesTargetByClient.name = entry.getKey();

            // Calculamos y establecemos el subTotal
            BigDecimal subTotal = entry.getValue().stream()
                    .map(Sales::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            salesTargetByClient.subTotal = NumberFormat.separarMiles(subTotal);

            // Aquí asumo que debemos obtener el objetivo de compra de cada cliente.
            // Puedes cambiar esto si necesitas otro valor.

            // BigDecimal clientTarget = repository.getObjetivoCliente(salesTargetByClient.getName());

            BigDecimal clientTarget = entry.getValue().stream()
                    .map(Sales::getObjetivoDeCompra)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            salesTargetByClient.targetClient = NumberFormat.separarMiles(clientTarget);

            // Calculamos y establecemos el porcentaje
            BigDecimal percent = clientTarget.compareTo(BigDecimal.ZERO) > 0
                    ? subTotal.multiply(new BigDecimal(100)).divide(clientTarget, 2, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO;
            salesTargetByClient.percent = NumberFormat.separarMiles(percent.setScale(0, RoundingMode.HALF_UP)) + " %";

            // Añadimos el objeto al resultado
            resultList.add(salesTargetByClient);
        }

        // Ordenar alfabéticamente por name
        resultList.sort(Comparator.comparing(stbc -> stbc.name));

        return resultList;
    }

}