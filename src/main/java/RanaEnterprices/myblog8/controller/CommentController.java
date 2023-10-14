package RanaEnterprices.myblog8.controller;

import RanaEnterprices.myblog8.payload.CommentDto;
import RanaEnterprices.myblog8.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }





    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")  Long postId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
// public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto){
// return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);



    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentByPostId(postId);
    }




    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") Long postId ,
            @PathVariable(value ="id") Long commentId
    ){
        CommentDto commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO , HttpStatus.OK);

    }


//     http://localhost:8080/api/posts/comments
    @GetMapping("/comments")
    public List<CommentDto> getComments(){
        List<CommentDto> allComments = commentService.getAllComments();
        return allComments;
    }


    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateTheComments(
            @PathVariable("postId") long postId,
            @PathVariable("id") long commentId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dto = commentService.updateComment(postId,commentId,commentDto);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }





    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<String> deleteTheComments(
            @PathVariable("postId") long postId,
            @PathVariable("id") long commentId
    ){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment deleted successfully" , HttpStatus.OK);
    }

}
