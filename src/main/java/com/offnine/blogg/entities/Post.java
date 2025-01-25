package com.offnine.blogg.entities;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    
    @Column(name = "post_title")
    private String title;

    @Column(name = "content")
    private String content;

   
    private String imageName;

    
    private Date addedDate;
    
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;
  @ManyToOne
    private User user;

}
