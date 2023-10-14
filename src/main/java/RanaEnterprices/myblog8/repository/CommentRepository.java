package RanaEnterprices.myblog8.repository;


import RanaEnterprices.myblog8.entity.Comment;
import RanaEnterprices.myblog8.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  //  List<Comment> findByPostId(long postId);
    List<Comment> findByPostId(long postId);

}