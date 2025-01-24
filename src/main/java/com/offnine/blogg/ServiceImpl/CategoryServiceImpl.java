 package com.offnine.blogg.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offnine.blogg.Payload.CategoryDto;
import com.offnine.blogg.Repo.CategoryRepo;
import com.offnine.blogg.Services.CategoryService;
import com.offnine.blogg.entities.Categories;
import com.offnine.blogg.exception.ResourceNotFoundException;

@Service
 public class CategoryServiceImpl implements CategoryService {
    @Autowired
private CategoryRepo categoryRepo;
@Autowired
private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
    Categories cat = this.modelMapper.map(categoryDto, Categories.class);
    Categories addedCate = this.categoryRepo.save(cat);
    return this.modelMapper.map(addedCate, CategoryDto.class);

    }
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId){
        Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Categories updatedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId){
        Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
 this.categoryRepo.delete(cat);
    }

    
 @Override
public CategoryDto getCategoryById( Integer categoryId){
    Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
    return this.modelMapper.map(cat, CategoryDto.class);

}
@Override
public List<CategoryDto> getAllCategories() {
    List<Categories> categories = this.categoryRepo.findAll();
    List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
    return catDtos;
   
}
  
    
    

}