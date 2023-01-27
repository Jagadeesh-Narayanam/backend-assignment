package com.springframework.backendassignment.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supplierName;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "supplier")

    @JsonIgnore
    private List<InventoryData> inventoryData = new ArrayList<>();

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }
}

