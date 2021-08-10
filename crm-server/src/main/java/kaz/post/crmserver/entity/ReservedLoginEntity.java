package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_RESERVED_LOGIN")
@SequenceGenerator(name = "id_gen", sequenceName = "reserved_login_seq", initialValue = 60000000, allocationSize = 1)
public class ReservedLoginEntity extends AbstractAuditingEntity {

	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
