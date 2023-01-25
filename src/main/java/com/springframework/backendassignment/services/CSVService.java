package com.springframework.backendassignment.services;

import com.springframework.backendassignment.helpers.CSVHelper;
import com.springframework.backendassignment.model.InventoryData;
import com.springframework.backendassignment.repositories.InventoryDataRepository;
import com.springframework.backendassignment.repositories.ProductRepository;
import com.springframework.backendassignment.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {
    private InventoryDataRepository inventoryDataRepository;
    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;

    public CSVService(InventoryDataRepository inventoryDataRepository, ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.inventoryDataRepository = inventoryDataRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public void save(MultipartFile file){
        try{
            List[] outputLists = CSVHelper.csvToInventories(file.getInputStream());

            productRepository.saveAll(outputLists[0]);
            supplierRepository.saveAll(outputLists[1]);
            inventoryDataRepository.saveAll(outputLists[2]);
//            List<InventoryData> inventoryDataList = outputLists.get(0);
//            inventoryDataRepository.saveAll(outputLists.get(0));
            System.out.println("File Uploaded");

        }
        catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }

    }
}
