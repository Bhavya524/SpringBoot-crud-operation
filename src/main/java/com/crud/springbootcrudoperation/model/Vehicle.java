package com.crud.springbootcrudoperation.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Vehicle{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String company;

    private String years;


}
