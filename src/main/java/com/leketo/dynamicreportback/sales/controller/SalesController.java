package com.leketo.dynamicreportback.sales.controller;
import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
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

    private final SalesService salesService;

    @GetMapping("/find_by_date")
    public ResponseEntity<List<Root>> getForParams(
            @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to
    ) {
        System.out.println("from:::"+ from);
        System.out.println("to:::"+ to);
        return ResponseEntity.ok(salesService.findByDate(from, to));
    }


}
