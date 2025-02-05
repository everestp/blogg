package com.offnine.blogg.Repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offnine.blogg.entities.Categories;
import com.offnine.blogg.entities.Post;
import com.offnine.blogg.entities.User;


public interface PostRepo extends JpaRepository<Post,Integer>{
  List<Post> findByUser(User user);
   List<Post> findByCategories(Categories categories);
    List<Post> findByTitleContaining(String title);
     

}
