package com.abdi.abdi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Can not be blank")
    private String fistName;
    @NotBlank(message = "Can not be blank")
    private String lastName;
    @NotBlank(message = "Can not be blank")
    @Column(length = 10)
    private String org;
    @NotBlank(message = "Can not be blank")
    private String telefon;
    @NotBlank(message = "Can not be blank")
    private String adress;
    @NotBlank(message = "Can not be blank")
    private String ort;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<AmalHq> amal;
}
