package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.UserCacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserCacheRepository extends JpaRepository<UserCacheEntity, Long> {

	Optional<UserCacheEntity> findOneByActivationKey(String activationKey);

	List<UserCacheEntity> findByLoginAndActivationKey(String login, String activationKey);

	List<UserCacheEntity> findByActivationKey(String activationKey);

	List<UserCacheEntity> findByMobileNumber(String mobileNumber);

	UserCacheEntity findFirstByLoginOrderByIdDesc(String login);

	Optional<UserCacheEntity> findFirstByLoginAndMobileNumberOrderByIdDesc(String login, String mobileNumber);

	Optional<UserCacheEntity> findFirstByLoginAndActivationKeyOrderByIdDesc(String login, String key);

	@Transactional
	Optional<UserCacheEntity> findOneByInviteToken(String token);
}
