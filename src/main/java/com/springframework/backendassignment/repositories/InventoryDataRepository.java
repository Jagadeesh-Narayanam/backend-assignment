package com.springframework.backendassignment.repositories;

import com.springframework.backendassignment.model.InventoryData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryDataRepository extends JpaRepository<InventoryData,Long> {
    @Query("select i from InventoryData i where i.supplier in (select s.id from Supplier s where s.id=?1) and i.stock>0")
    Page<InventoryData> findBySupplierOnly(Long id, PageRequest of);

    @Query("select i from InventoryData i where i.supplier in (select s.id from Supplier s where s.id=?1) and i.product in (select p from Product p where p.name=?2) and i.stock>0")
    Page<InventoryData> findBySupplierAndProduct(Long id,String productName,PageRequest of);

    @Query("select i from InventoryData i where i.supplier in (select s.id from Supplier s where s.id=?1) and i.expiry>now()")
    Page<InventoryData> findBySupplierAndExpiredStatus(Long id,Boolean notExpired,PageRequest of);

    @Query("select i from InventoryData i where i.supplier in (select s.id from Supplier s where s.id = ?1 ) and i.product in (select p.id from Product p where p.name like %?2%) and i.expiry>now() and i.stock>0")
    Page<InventoryData> findBySupplierWithAllParams(Long id,String productName,Boolean notExpired,PageRequest of);


}
