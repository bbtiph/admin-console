package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.RegistrationCacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the RegistrationCache entity.
 */
@SuppressWarnings("unused")
public interface RegistrationCacheRepository extends JpaRepository<RegistrationCacheEntity, Long> {

	Optional<RegistrationCacheEntity> findOneByUid(String uid);

}
