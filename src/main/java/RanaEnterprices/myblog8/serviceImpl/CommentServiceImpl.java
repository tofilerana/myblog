package RanaEnterprices.myblog8.serviceImpl;


import RanaEnterprices.myblog8.entity.Comment;
import RanaEnterprices.myblog8.entity.Post;
import RanaEnterprices.myblog8.exception.ResourceNotFoundException;
import RanaEnterprices.myblog8.payload.CommentDto;
import RanaEnterprices.myblog8.repository.CommentRepository;
import RanaEnterprices.myblog8.repository.PostRepository;
import RanaEnterprices.myblog8.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepo;

    private final CommentRepository comRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository comRepo) {
        this.postRepo = postRepo;
        this.comRepo = comRepo;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {


        Post pst = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post" , "Id" , postId)

        );

        Comment comment = convertToEntity(commentDto);
        comment.setPost(pst);
        Comment savedComment = comRepo.save(comment);

        CommentDto updatedComment = convertToDto(savedComment);

        return updatedComment;
    }



    @Override
    public List<CommentDto> getCommentByPostId(long postId) {

        List<Comment> comments = comRepo.findByPostId(postId);
        return comments.stream().map(comment -> convertToDto(comment)).collect(Collectors.toList());

    }




    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post pst = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post" , "Id" , postId)
        );

        Comment comment = comRepo.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "id", commentId)
        );

       


        return convertToDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> all = comRepo.findAll();
        List<CommentDto> commentDtos = all.stream().map(s -> convertToDto(s)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        Post pst = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post" , "Id" , postId)
        );

        Comment comment = comRepo.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "id", commentId)
        );




        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = comRepo.save(comment);


        return convertToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {

        Post pst = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post" , "Id" , postId)
        );

        Comment comment = comRepo.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "id", commentId)
        );


        comRepo.deleteById(comment.getId());
    }


    private Comment convertToEntity(CommentDto commentDto) {

        Comment comment = new Comment();

        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());

        Post post = new Post();
        post.setId(commentDto.getPostId());
        comment.setPost(post);

        return comment;
    }

    private CommentDto convertToDto(Comment comment) {

        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        commentDto.setPostId(comment.getPost().getId());



        return commentDto;
    }
}
