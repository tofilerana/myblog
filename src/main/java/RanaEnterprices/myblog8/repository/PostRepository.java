package RanaEnterprices.myblog8.repository;


import RanaEnterprices.myblog8.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
// post is Entity and Long is Wrapper class from Entity.
