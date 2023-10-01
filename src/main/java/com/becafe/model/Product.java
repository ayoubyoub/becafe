package com.becafe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @Column(name="productID", unique = true)
    private String productID;

    @Column(name="designation")
    private String designation;
}