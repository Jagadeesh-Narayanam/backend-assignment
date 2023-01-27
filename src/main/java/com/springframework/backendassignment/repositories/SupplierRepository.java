package com.springframework.backendassignment.repositories;

import com.springframework.backendassignment.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findById(Long id);

    @Query("select s.id from Supplier s where s.supplierName =?1")
    Long findSupplierIdBySupplierName(String supplierName);
}
