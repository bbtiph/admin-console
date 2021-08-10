package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.reservation.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepositoryN extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByLogin(String login);

    @Transactional
    Optional<UserEntity> findOneByLogin(String login);

    @Query("select ue.login from UserEntity ue where ue.login = :userLogin")
    String findByLogin(@Param("userLogin") String userLogin);
}
