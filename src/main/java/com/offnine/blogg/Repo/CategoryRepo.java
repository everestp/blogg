package com.offnine.blogg.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offnine.blogg.entities.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer> {

    
}
