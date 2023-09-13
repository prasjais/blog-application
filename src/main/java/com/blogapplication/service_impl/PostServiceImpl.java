package com.blogapplication.service_impl;
import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.PostDTO;
import com.blogapplication.payloads.PostResponse;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryIO", categoryId));

        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post saved = this.postRepo.save(post);
        return this.modelMapper.map(saved, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        post.setPostId(postDTO.getPostId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        post.setAddedDate(new Date());

        Post saved = this.postRepo.save(post);
        return this.modelMapper.map(saved, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDTO getPost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> singlePageList = this.postRepo.findAll(page);
        List<Post> post = singlePageList.getContent();
        List<PostDTO> postDTOList = post.stream().map(p->this.modelMapper.map(p,PostDTO.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(singlePageList.getNumber());
        postResponse.setPageSize(singlePageList.getSize());
        postResponse.setTotalRecords(singlePageList.getTotalElements());
        postResponse.setTotalPages(singlePageList.getTotalPages());
        postResponse.setLastPage(singlePageList.isLast());

        return postResponse;
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> catList = this.postRepo.findByCategory(cat);
        List<PostDTO> postList = catList.stream().map(c -> this.modelMapper.map(c, PostDTO.class)).collect(Collectors.toList());

        return postList;
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> postList = this.postRepo.findByUser(user);
        List<PostDTO> list = postList.stream().map(u->this.modelMapper.map(u, PostDTO.class)).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<PostDTO> searchTitle(String keyword) {

        List<Post> post = this.postRepo.findByTitleContaining(keyword);
        List<PostDTO> postDTOList = post.stream().map(p->this.modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }
}
