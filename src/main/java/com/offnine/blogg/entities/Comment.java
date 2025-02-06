package com.offnine.blogg.entities;



import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String content;
    @ManyToOne
    @JoinColumn(name="postId")
    private Post post;
    
    
}
