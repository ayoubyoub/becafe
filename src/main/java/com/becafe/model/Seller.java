package com.becafe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sellers")
public class Seller extends User {

    @Column(name = "seller")
    private String seller;
}
