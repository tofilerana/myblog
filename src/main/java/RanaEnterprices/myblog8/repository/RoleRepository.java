package RanaEnterprices.myblog8.repository;

import RanaEnterprices.myblog8.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
//With the help of this line we will be able to find the <Role> based on the name of the user.

}
