package kaz.post.crmserver.repositories.mail;

import kaz.post.crmserver.entity.BookingEntity;
import kaz.post.crmserver.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data JPA repository for the Organization entity.
 */
public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {

	@Transactional
	@Query("select count(*) from TrackingEntity where created_date >= TO_DATE(:begData, 'YYYYMMDD') and created_date < TO_DATE(:endData, 'YYYYMMDD')")
	int getCountByMonth(@Param("begData") String begData,
						@Param("endData") String endData);

}
