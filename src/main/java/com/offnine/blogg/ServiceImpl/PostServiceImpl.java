
package com.offnine.blogg.ServiceImpl;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.offnine.blogg.Payload.PostDto;
import com.offnine.blogg.Payload.UserDto;
import com.offnine.blogg.Repo.CategoryRepo;
import com.offnine.blogg.Repo.PostRepo;
import com.offnine.blogg.Repo.UserRepo;
import com.offnine.blogg.Services.PostService;
import com.offnine.blogg.entities.Categories;
import com.offnine.blogg.entities.Post;
import com.offnine.blogg.entities.User;
import com.offnine.blogg.exception.ResourceNotFoundException;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    
  
    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
            User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
            Categories categories = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
    System.out.println(user.getName());
            Post post = this.modelMapper.map(postDto, Post.class);
            post.setImageName("default.png");
            post.setAddedDate(new Date(System.currentTimeMillis()));
            post.setUser(user);
            post.setCategories(categories);
         
           
            Post newPost = this.postRepo.save(post);

            return this.modelMapper.map(newPost , PostDto.class);
            
            

    }

    @Override
    public PostDto upadatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID",postId));
post.setTitle(postDto.getTitle());
post.setContent(postDto.getContent());
post.setImageName(postDto.getImageName());
Post updatedPost = this.postRepo.save(post);
return this.modelMapper.map(updatedPost,PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
         List<Post> posts = this.postRepo.findAll();
      List<PostDto> postDtos =posts.stream().map((cat) -> this.modelMapper.map(cat, PostDto.class)).collect(Collectors.toList());
return  postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID",postId));
                return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Post","Category ID",categoryId));
       List<Post> posts = this.postRepo.findByCategories(cat);
      List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
      return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Post","User Id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
       List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
       return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
    return null;
    }
   
}
