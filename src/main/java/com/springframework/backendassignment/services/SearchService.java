package com.springframework.backendassignment.services;

import com.springframework.backendassignment.model.InventoryData;
import com.springframework.backendassignment.repositories.InventoryDataRepository;
import com.springframework.backendassignment.repositories.ProductRepository;
import com.springframework.backendassignment.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private SupplierRepository supplierRepository;
    private InventoryDataRepository inventoryDataRepository;
    private ProductRepository productRepository;

    public SearchService(SupplierRepository supplierRepository, InventoryDataRepository inventoryDataRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.inventoryDataRepository = inventoryDataRepository;
        this.productRepository = productRepository;
    }

    public List<InventoryData> getInventoryBySupplierId(Long id,String productName,Boolean notExpired){
//        System.out.println(inventoryDataRepository.findBySupplierId(id));
        return inventoryDataRepository.findBySupplierId(id,productName,notExpired);
    }
    public List<InventoryData> getInventoryBySupplierName(String supplierName){
        return inventoryDataRepository.findInventoryDataBySupplier_SupplierName(supplierName);
    }
}
