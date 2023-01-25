package com.springframework.backendassignment.repositories;

import com.springframework.backendassignment.model.InventoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryDataRepository extends JpaRepository<InventoryData,Long> {
    @Query("select i from InventoryData i where i.supplier in (select s.id from Supplier s where s.id = ?1 ) and i.product in (select p.id from Product p where p.name like %?2%) or (?3=true and i.expiry>now())")
    List<InventoryData> findBySupplierId(Long id,String productName,Boolean notExpired);

    @Query("select i from InventoryData i where i.supplier in (select s.id from Supplier s where s.supplierName LIKE %?1%)")
    List<InventoryData> findInventoryDataBySupplier_SupplierName(String supplierName);
}
