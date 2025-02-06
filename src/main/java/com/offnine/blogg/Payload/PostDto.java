package com.offnine.blogg.Payload;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PostDto {
 
    private Integer postId;

    private String title;

   
    private String content;

   
    private String imageName;

    
    private Date addedDate;
    

    private CategoryDto categories;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
