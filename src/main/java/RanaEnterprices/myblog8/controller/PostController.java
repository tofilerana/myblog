package RanaEnterprices.myblog8.controller;



import RanaEnterprices.myblog8.payload.PostDto;
import RanaEnterprices.myblog8.service.PostService;
import RanaEnterprices.myblog8.serviceImpl.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
// This will give us API.
@RequestMapping("/api/post")
// This will help us to create the link. and here we are creating the link because if we create the link here
// it will be common for all the method.
public class PostController {

    private final PostService postService;
    // Here we are calling the service layer.
    public PostController(PostService postService) {
        this.postService = postService;
    }// HERE WE ARE CREATING CONSTRUCTOR.



    //http://localhost:8080/api/post

//By PreAuthorize annotation we are saying that bellow functionality can be accessed only when you are login as ADMIN

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto , BindingResult result){

        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() ,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Now we will save the data
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
        // ResponseEntity will help us to Return the status code of a particular thing.

    }


    //HOW TO GET ALL THE RECORDS ???????

//
//    @GetMapping
//    public List<PostDto> getAllPosts(){
//       return postService.getAllPosts();
//    }// by this method we are getting all the records together.


    //HOW TO GET ALL THE RECORDS ??????? DURING PAGINATION

    //http://localhost:8080/api/post?pageNo=0&pageSize=10&sortBy=title&sortDir=asc

    @GetMapping("/get")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false) int pageNo ,
            @RequestParam(value = "pageSize" , defaultValue = "10" , required = false) int pageSize ,
            @RequestParam(value = "sortBy" , defaultValue = "id" , required = false) String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = "asc" , required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo , pageSize , sortBy , sortDir);
    }// by this method we are getting all the records together.




    // NOW WE WILL SEE HOW TO GET THE RECORD BY ID

    //http://localhost:8080/api/post/1
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostByID(@PathVariable("id") long id){
        return  new ResponseEntity<>(postService.getPostByID(id) , HttpStatus.OK);
    }

    // how to update your record

    //http://localhost:8080/api/post/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") long id){
        PostDto postResponse = postService.updatePost(postDto , id);

        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    // how to delete data

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePost(@PathVariable("id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<String>("Post is Deleted !!" , HttpStatus.OK);
    }

}
