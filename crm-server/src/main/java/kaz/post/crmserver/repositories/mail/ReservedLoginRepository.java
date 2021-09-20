package kaz.post.crmserver.repositories.mail;

import kaz.post.crmserver.entity.ReservedLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Spring Data JPA repository for the ReservedLogin entity.
 */
public interface ReservedLoginRepository extends JpaRepository<ReservedLoginEntity, Long> {

	@Transactional
	Optional<ReservedLoginEntity> findOneByLogin(String login);

}
