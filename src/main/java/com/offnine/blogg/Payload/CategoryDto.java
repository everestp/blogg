package com.offnine.blogg.Payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CategoryDto {
  
    private Integer categoryId;

@NotBlank
@Size(min=4 ,message = "Pease enter vasid category of min size 4")
    private String categoryTitle;
    @NotBlank
    @Size(min=4 ,message = "Pease enter vaid category Description of min size 4")
    private String categoryDescription;
    
}
