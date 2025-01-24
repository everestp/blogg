package com.offnine.blogg.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.offnine.blogg.Payload.CategoryDto;

@Service
public interface CategoryService {

    


    // create
   CategoryDto createCategory(CategoryDto categoryDto);



    //update

CategoryDto updateCategory(CategoryDto categoryDto ,Integer catid);
    //delete
void deleteCategory(Integer categoryId);

    //get
CategoryDto getCategoryById(Integer categoryId);

//get All
List<CategoryDto> getAllCategories();
    
}
