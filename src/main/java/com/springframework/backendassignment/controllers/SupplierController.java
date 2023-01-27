package com.springframework.backendassignment.controllers;

import com.springframework.backendassignment.model.InventoryData;
import com.springframework.backendassignment.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/csv")
public class SupplierController {
    private SearchService searchService;

    public SupplierController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("supplier/{supplier}")
    Page<InventoryData> getInventories(@PathVariable String supplier,
                                       @RequestParam(defaultValue = "") String productName,
                                       @RequestParam(defaultValue = "false") Boolean notExpired,
                                       @RequestParam(defaultValue = "0")int offset,
                                       @RequestParam(defaultValue = "5") int pageSize){
        try{
            Long supplierId = Long.parseLong(supplier);

            return searchService.getInventoryBySupplierId(supplierId,productName,notExpired,offset,pageSize);
        }
        catch(Exception e){
            Long supplierId = searchService.getSupplierIdBySupplierName(supplier);
            return searchService.getInventoryBySupplierId(supplierId,productName,notExpired,offset,pageSize);
        }

    }
//    @GetMapping("supplier/name/{supplier_name}")
//    Page<InventoryData> getInventories(@PathVariable String supplier_name,
//                                       @RequestParam(defaultValue = "") String productName,
//                                       @RequestParam(defaultValue = "false") Boolean notExpired,
//                                       @RequestParam(defaultValue = "0")int offset,
//                                       @RequestParam(defaultValue = "5") int pageSize){
//        Long supplierId = searchService.getSupplierIdBySupplierName(supplier_name);
//        return searchService.getInventoryBySupplierId(supplierId,productName,notExpired,offset,pageSize);
//    }
}
