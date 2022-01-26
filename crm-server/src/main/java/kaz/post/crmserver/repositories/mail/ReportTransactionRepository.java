package kaz.post.crmserver.repositories.mail;

import kaz.post.crmserver.entity.ReportTransactionEntity;
import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.entity.UserServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data JPA repository for the organization's.
 */
public interface ReportTransactionRepository extends JpaRepository<ReportTransactionEntity, Long> {

    @Transactional
    @Query("SELECT u FROM ReportTransactionEntity u")
    Page<ReportTransactionEntity> findTransactionsBetweenTwoDate(
            @Param("start") String start,
            @Param("end") String end,
            Pageable pageable);

    @Transactional
    @Query("SELECT u FROM ReportTransactionEntity u")
    List<ReportTransactionEntity> findTransactionsBetweenTwoDateWithOutPageable(
            @Param("start") String start,
            @Param("end") String end);

    @Transactional
    @Query("SELECT count(u) FROM ReportTransactionEntity u")
    Long findCountTransactionsBetweenTwoDate(
            @Param("start") String start,
            @Param("end") String end);
}
