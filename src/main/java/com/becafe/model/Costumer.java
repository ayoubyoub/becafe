package com.becafe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "costumers")
public class Costumer extends User {

    @Column(name = "costumer")
    private String costumer;

}
