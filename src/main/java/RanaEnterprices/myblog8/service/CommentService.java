package RanaEnterprices.myblog8.service;




import RanaEnterprices.myblog8.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId , CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(Long postId , Long commentId);

    List<CommentDto> getAllComments();
    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
