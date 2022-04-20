package kaz.post.crmserver.repositories.mail;

import kaz.post.crmserver.entity.LoginBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by user on 30.03.2015.
 */
public interface LoginBlockRepository extends JpaRepository<LoginBlockEntity, Long> {

	@Query("select a from LoginBlockEntity a where login = ?1")
	public List<LoginBlockEntity> findByLogin(String login);

	@Query("select a from LoginBlockEntity a where login = ?1 and ipAddress =?2")
	public LoginBlockEntity findOneByLoginAndIpAddress(String login, String ipAddress);

	@Query("select a from LoginBlockEntity a where login = ?1 and ipAddress =?2 and type = ?3")
	public LoginBlockEntity findOneByLoginAndIpAddressAndType(String login, String ipAddress, String type);

	@Query("select a from LoginBlockEntity a where login = ?1 and type =?2")
	public LoginBlockEntity findOneByLoginAndType(String login, String type);

}
