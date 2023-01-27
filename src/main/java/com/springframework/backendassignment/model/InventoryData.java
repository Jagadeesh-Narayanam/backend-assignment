package com.springframework.backendassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class InventoryData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String batch;
    private Integer stock;
    private Integer deal;
    private Integer free;
    private Float mrp;
    private Float rate;
    private Date expiry;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public InventoryData(Product product, String batch, Integer stock, Integer deal, Integer free, Float mrp, Float rate, Supplier supplier) {
        this.product = product;
        this.batch = batch;
        this.stock = stock;
        this.deal = deal;
        this.free = free;
        this.mrp = mrp;
        this.rate = rate;
        this.supplier = supplier;
    }
}
