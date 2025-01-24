package com.offnine.blogg.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offnine.blogg.Payload.CategoryDto;
import com.offnine.blogg.Services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/api/cat")
public class CategoryController {
    @Autowired
private CategoryService categoryService;    
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCatagory(@Valid  @RequestBody CategoryDto categoryDto) {
      CategoryDto createCategory= this.categoryService.createCategory(categoryDto);
      return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
    }
    
//update
@PutMapping("/{catId}")
public ResponseEntity<CategoryDto> updateCatogory( @Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer catId) {
    CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
    
    return new  ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
}
//delete
@DeleteMapping("/{catId}")
public ResponseEntity<Void> deleteCategory(@PathVariable Integer catId) {
    this.categoryService.deleteCategory(catId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
 @GetMapping("/{catId}")
 public ResponseEntity<CategoryDto> getCatogory(@PathVariable Integer catId) {
    CategoryDto categoryDto = this.categoryService.getCategoryById(catId);
     return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
 }
 
 @GetMapping("/")
 public ResponseEntity<List<CategoryDto>> getCatogoris() {
    List<CategoryDto>  categories= this.categoryService.getAllCategories();
     return ResponseEntity.ok(categories);
 }
}
