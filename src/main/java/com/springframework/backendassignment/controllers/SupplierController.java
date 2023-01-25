package com.springframework.backendassignment.controllers;

import com.springframework.backendassignment.model.InventoryData;
import com.springframework.backendassignment.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/csv")
public class SupplierController {
    private SearchService searchService;

    public SupplierController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping("supplier/{supplier_id}")
    List<InventoryData> getInventories(@PathVariable Long supplier_id,
                                       @RequestParam(defaultValue = "") String productName,
                                       @RequestParam(defaultValue = "false") Boolean notExpired){
        return searchService.getInventoryBySupplierId(supplier_id,productName,notExpired);
    }
//    @GetMapping("supplier/{supplier_name}")
//    List<InventoryData> getInventories(@PathVariable String supplier_name){
//        return searchService.getInventoryBySupplierName(supplier_name);
//    }
}
