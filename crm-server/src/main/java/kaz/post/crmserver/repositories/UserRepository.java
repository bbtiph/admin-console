package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.reservation.UserEntity;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    void delete(UserEntity t);

    List<UserEntity> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    @Transactional
    Page<UserEntity> findAll(Pageable pageable);

    @Transactional
    Optional<UserEntity> findOneByIinAndConfirmedIsTrue(String iin);

    @Transactional
    Optional<UserEntity> findOneByIin(String iin);

    boolean existsByLogin(String login);

    @Transactional
    Optional<UserEntity> findOneByLogin(String login);

    @Transactional
    UserEntity findByLogin(String login);

    @Transactional
    UserEntity findByLoginAndActivated(String login, boolean activated);

    @Transactional
    UserEntity findByLoginIgnoreCaseOrIinOrMobileNumber(String login, String iin, String mobileNumber);

    @Transactional
    Optional<UserEntity> findByLoginOrIin(String login, String iin);

    @Transactional
    UserEntity findByTelegramUserIdIgnoreCase(String telegramUserId);

    @Query("select count(u) from UserEntity u")
    long countUser();

    Optional<List<UserEntity>> findAllByMobileNumber(String mobileNumber);

    Optional<UserEntity> findFirstByMobileNumber(String mobileNumber);

    Optional<List<UserEntity>> findAllByIin(String iin);

    @Transactional
    Optional<UserEntity> findOneByLoginAndMobileNumber(String login, String mobileNumber);

    @Transactional
    @Query("SELECT u FROM UserEntity u WHERE (u.login=:login AND u.mobileNumber=:mobileNumber) OR (u.iin=:iin AND u.mobileNumber=:mobileNumber)")
    Optional<UserEntity> findOneByLoginOrIinAndMobileNumber(
            @Param("login") String login,
            @Param("iin") String iin,
            @Param("mobileNumber") String mobileNumber);
}
