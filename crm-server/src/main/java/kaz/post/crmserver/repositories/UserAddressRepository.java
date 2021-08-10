package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the user's address entity.
 */
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

	List<UserAddressEntity> findByUserId(Long userId);

	List<UserAddressEntity> findByUserLogin(String userLogin);

}
