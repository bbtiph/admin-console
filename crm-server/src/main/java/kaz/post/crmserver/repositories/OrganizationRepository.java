package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Organization entity.
 */
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

	Optional<OrganizationEntity> findOneByActivationKey(String activationKey);

	@Transactional
	Optional<OrganizationEntity> findOneByBin(String bin);
	@Transactional
	Optional<OrganizationEntity> findOneByPhoneNumber(String phoneNumber);
	@Transactional
	Optional<OrganizationEntity> findOneByMobileNumber(String mobileNumber);
	@Transactional
	Optional<List<OrganizationEntity>> findAllByMobileNumber(String mobileNumber);
	@Transactional
	Optional<OrganizationEntity> findOneByLogin(String login);
	@Transactional
	Optional<OrganizationEntity> findOneByLoginOrBinIgnoreCase(String login, String bin);
	@Transactional
	Optional<OrganizationEntity> findOneByLoginIgnoreCase(String login);
	@Transactional
	Optional<OrganizationEntity> findOneByLoginAndMobileNumber(String login, String mobileNumber);
	@Transactional
	@Query("SELECT u FROM OrganizationEntity u WHERE (u.login=:login AND u.mobileNumber=:mobileNumber) OR (u.bin=:bin AND u.mobileNumber=:mobileNumber)")
	Optional<OrganizationEntity> findOneByLoginOrBinAndMobileNumber(
		@Param("login") String login,
		@Param("bin") String bin,
		@Param("mobileNumber") String mobileNumber);
}
