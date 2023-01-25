package com.springframework.backendassignment.repositories;

import com.springframework.backendassignment.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    public Optional<Supplier> findById(Long id);
    public Optional<Supplier> findBySupplierName(Supplier supplier);
}
