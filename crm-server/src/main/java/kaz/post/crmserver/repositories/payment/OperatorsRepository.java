package kaz.post.crmserver.repositories.payment;

import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.entity.payment.Operators;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface OperatorsRepository extends JpaRepository<Operators, Long> {
}
