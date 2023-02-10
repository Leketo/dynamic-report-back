package com.leketo.dynamicreportback.sales.service;

import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.model.Sales;
import com.leketo.dynamicreportback.sales.model.SubRow;
import com.leketo.dynamicreportback.sales.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository repository;

    public List<Root> getAll2(){
        List<Sales> sales = repository.getAll();
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
            root.setSubTotal(subTotal);

            List<BigDecimal> targets = salesBySeller.stream().map(Sales::getObjetivoDelVendedor).collect(Collectors.toList());
            BigDecimal target = targets.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            root.setTarget(target);

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
                subRowAux.setSubTotal(targetFamilia);

                Map<String, List<Sales>> subfamily = familes.stream()
                        .collect(Collectors.groupingBy(Sales::getSubFamilia));

                //SUB-FAMILIA
                for (Map.Entry<String, List<Sales>> subFa : subfamily.entrySet()) {
                    SubRow subFamilyRow = new SubRow();
                    subFamilyRow.setName(subFa.getKey());
                    List<Sales> subFamilia = subFa.getValue();
                    List<BigDecimal> subTotalsSubFamilia = subFamilia.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                    BigDecimal subTotalSubFamilia = subTotalsSubFamilia.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

                    subFamilyRow.setSubTotal(subTotalSubFamilia);
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
}
