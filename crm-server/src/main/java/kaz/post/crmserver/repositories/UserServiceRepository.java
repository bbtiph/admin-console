package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.UserServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the organization's.
 */
public interface UserServiceRepository extends JpaRepository<UserServiceEntity, Long> {

	List<UserServiceEntity> findByBinAndUserId(String bin, Long userId);

	Integer countByBinAndUserId(String bin, Long userId);
}
