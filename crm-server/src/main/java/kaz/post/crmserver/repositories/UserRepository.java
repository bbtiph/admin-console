package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.UserEntity;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    void delete(UserEntity t);

    List<UserEntity> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    @Transactional
    Page<UserEntity> findAll(Pageable pageable);

    @Transactional
    Page<UserEntity> findAllByIinContains(String iin, Pageable pageable);

    @Transactional
    Page<UserEntity> findAllByFirstNameContains(String firstname, Pageable pageable);

    @Transactional
    Page<UserEntity> findAllByLastNameContains(String lastname, Pageable pageable);

    @Transactional
    Page<UserEntity> findAllByMiddleNameContains(String middlename, Pageable pageable);

    @Transactional
    Page<UserEntity> findAllByMobileNumberContains(String phonenum, Pageable pageable);

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

    @Transactional
    @Query("SELECT u FROM UserEntity u WHERE (u.createdDate BETWEEN to_date(:start,'yyyy-MM-dd') and to_date(:end,'yyyy-MM-dd'))")
    Page<UserEntity> findUsersBetweenTwoDate(
            @Param("start") String start,
            @Param("end") String end,
            Pageable pageable);
    @Transactional
    @Query("SELECT u FROM UserEntity u WHERE (u.createdDate BETWEEN to_date(:start,'yyyy-MM-dd') and to_date(:end,'yyyy-MM-dd'))")
    List<UserEntity> findUsersBetweenTwoDateWithOutPageable(
            @Param("start") String start,
            @Param("end") String end);

    @Transactional
    @Query("SELECT count(u) FROM UserEntity u WHERE (u.createdDate BETWEEN to_date(:start,'yyyy-MM-dd') and to_date(:end,'yyyy-MM-dd'))")
    Long findCountUsersBetweenTwoDate(
            @Param("start") String start,
            @Param("end") String end);

    @Transactional
    @Query("SELECT (u) FROM UserEntity u WHERE (u.createdDate BETWEEN to_date('2020-01-01','yyyy-MM-dd') and to_date('2021-11-11','yyyy-MM-dd'))")
    List<UserEntity> findAllwithlimit();
}
