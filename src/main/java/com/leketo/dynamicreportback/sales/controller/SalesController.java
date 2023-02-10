package com.leketo.dynamicreportback.sales.controller;
import com.leketo.dynamicreportback.sales.model.Root;
import com.leketo.dynamicreportback.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

        @GetMapping("/find_all")
    public ResponseEntity<List<Root>> getAll() {
        return ResponseEntity.ok(salesService.getAll2());
    }


}
