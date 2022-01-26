package kaz.post.crmserver.repositories.mail;

import kaz.post.crmserver.entity.OrganizationEntity;
import kaz.post.crmserver.entity.ParcelLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Organization entity.
 */
public interface ParcelLogRepository extends JpaRepository<ParcelLogEntity, Long> {

	@Transactional
	@Query("select count(*) from ParcelLogEntity where created_date >= TO_DATE(:begData, 'YYYYMMDD') and created_date < TO_DATE(:endData, 'YYYYMMDD') and type is not null")
	int getCountByMonth(@Param("begData") String begData,
						@Param("endData") String endData);

}
