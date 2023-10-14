package RanaEnterprices.myblog8.service;



import RanaEnterprices.myblog8.payload.PostDto;
import RanaEnterprices.myblog8.serviceImpl.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);


    PostResponse getAllPosts(int pageNo , int pageSize , String sortBy , String sortDir);


    PostDto getPostByID(long id);


    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}