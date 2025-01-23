package com.offnine.blogg.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  int id;
    @Column
    private  String name;

    @Column
    private  String email;

    @Column
    private  String password;

    @Column
    private  int age;

    @Column
    private  String gender;



}
