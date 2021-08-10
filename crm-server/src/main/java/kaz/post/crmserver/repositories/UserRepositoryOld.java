package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepositoryOld extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
