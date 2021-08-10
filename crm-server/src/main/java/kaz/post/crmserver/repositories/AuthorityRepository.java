package kaz.post.crmserver.repositories;

import kaz.post.crmserver.entity.AuthorityEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {
    AuthorityEntity findFirstByName(String name);
}
