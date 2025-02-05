
package com.offnine.blogg.ServiceImpl;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.offnine.blogg.Payload.PostDto;
import com.offnine.blogg.Payload.PostResponse;
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
// impementation of pagination
    @Override
    public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        Sort sort =null;
        if(sortDir.equalsIgnoreCase("asc")){
           sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }
        PageRequest p =  PageRequest.of(pageNumber, pageSize,sort);

         Page<Post> pagePost = this.postRepo.findAll(p);
         List<Post> allPosts = pagePost.getContent();
      List<PostDto> postDtos =allPosts.stream().map((cat) -> this.modelMapper.map(cat, PostDto.class)).collect(Collectors.toList());
      PostResponse postResponse = new PostResponse();
      postResponse.setContent(postDtos);
      postResponse.setPageNumber(pagePost.getNumber());
      postResponse.setPageSize((pagePost.getSize()));
      postResponse.setTotalElements(pagePost.getTotalElements());
      postResponse.setTotalPages(pagePost.getTotalPages());
      postResponse.setLastPage(pagePost.isLast());
return  postResponse;
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
