package kaz.post.crmserver.repositories.payment;

import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.entity.payment.OfflineOperator;
import kaz.post.crmserver.entity.payment.Operators;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface OfflineOperatorsRepository extends JpaRepository<OfflineOperator, Long> {
    @Transactional
    Page<OfflineOperator> findAllByContractorNameContains(String contractorName, Pageable pageable);
    @Transactional
    @Query("SELECT u FROM OfflineOperator u WHERE (u.online=:type)")
    Page<OfflineOperator> findAllByOnline(@Param("type") Boolean type, Pageable pageable);
    @Transactional
    @Query("SELECT u FROM OfflineOperator u WHERE (u.enabled=:type)")
    Page<OfflineOperator> findAllByEnabled(@Param("type") Boolean type, Pageable pageable);
    @Transactional
    Page<OfflineOperator> findAll(Pageable pageable);
    @Query(value = "select count(u) from OfflineOperator u")
    long getAllOperatorsCount();
}
