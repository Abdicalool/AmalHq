package com.abdi.abdi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AmalHq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "branch_name",nullable = false)
    @NotBlank(message = "Name can not be blank")
    private String branchName;
    @Column(name = "branch_sign",nullable = false,length = 6)
    @NotBlank(message = "Sign can not be blank")
    private String sign;
    @Column(name = "deyn_allow")
    private Boolean deyn=false;
    @Column(name = "transaction_allow")
    private Boolean transactionAllow = false;
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

}
