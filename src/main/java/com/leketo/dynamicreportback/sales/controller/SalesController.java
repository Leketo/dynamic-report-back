package com.leketo.dynamicreportback.sales.controller;
import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.model.salesByCustomer.SalesByCustomer;
import com.leketo.dynamicreportback.sales.service.SalesService;
import com.leketo.dynamicreportback.util.NumberFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/sales-by-seller-and-family-subfamily_by_date")
    public ResponseEntity<List<Root>> salesBySellerAndFamilySubfamily(
            @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to
    ) {
        System.out.println("from:::"+ from);
        System.out.println("to:::"+ to);
        return ResponseEntity.ok(salesService.salesBySellerAndFamilySubfamily(from, to));
    }


    
    @GetMapping("/sales-by-seller-and-customer")
    public ResponseEntity<List<SalesByCustomer>> salesBySellerAndCustomer(
            @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to
    ) {
        System.out.println("from:::"+ from);
        System.out.println("to:::"+ to);
        return ResponseEntity.ok(salesService.salesBySellerAndCustomer(from, to));
    }

    @GetMapping("/sales-by-family-and-customer")
    public ResponseEntity<List<SalesByCustomer>> salesByFamilyAndCustomer(
            @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to
    ) {
        System.out.println("from:::"+ from);
        System.out.println("to:::"+ to);
        return ResponseEntity.ok(salesService.salesByFamilyAndCustomer(from, to));
    }


}
