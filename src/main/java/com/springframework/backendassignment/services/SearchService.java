package com.springframework.backendassignment.services;

import com.springframework.backendassignment.model.InventoryData;
import com.springframework.backendassignment.repositories.InventoryDataRepository;
import com.springframework.backendassignment.repositories.ProductRepository;
import com.springframework.backendassignment.repositories.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
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

    public Page<InventoryData> getInventoryBySupplierId(Long id, String productName, Boolean notExpired,int offset,int pageSize){
//        System.out.println(inventoryDataRepository.findBySupplierId(id));
        if(productName.equals("") && notExpired==false){
            log.info("search by supplier only");
            return inventoryDataRepository.findBySupplierOnly(id, PageRequest.of(offset,pageSize));
        }
        else if(!productName.equals("") && notExpired==false){
            log.info("search by supplier and productname");
            return inventoryDataRepository.findBySupplierAndProduct(id,productName,PageRequest.of(offset,pageSize));
        }
        else if(notExpired==true && productName.equals("")){
            log.info("search by supplier and not expired");
            return inventoryDataRepository.findBySupplierAndExpiredStatus(id,notExpired,PageRequest.of(offset,pageSize));
        }
        else {
            log.info("search by all params");
            return inventoryDataRepository.findBySupplierWithAllParams(id, productName, notExpired,PageRequest.of(offset,pageSize));
        }
    }
    public Long getSupplierIdBySupplierName(String supplierName){
        return supplierRepository.findSupplierIdBySupplierName(supplierName);
    }
}
