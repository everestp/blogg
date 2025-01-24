package com.offnine.blogg.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categories{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer categoryId;

    @Column(name="title")
    private String categoryTitle;

    @Column(name="description")
    private String categoryDescription;

}
