package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * A user.
 */
@Entity
@Table(name = "T_LOGIN_BLOCK")
@SequenceGenerator(name = "id_gen", sequenceName = "login_block_seq", initialValue = 60000000, allocationSize = 1)
//ALTER TABLE t_user DROP CONSTRAINT t_user_mobile_number_key
//ALTER TABLE t_user DROP CONSTRAINT uk_ss3vam3osia0b44ucn32ym3dh
public class LoginBlockEntity extends AbstractAuditingEntity {

	@Column(name = "login")
	private String login;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "attempts")
	private Long attempts;

	@Column(name = "type")
	private String type;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getAttempts() {
		return attempts;
	}

	public void setAttempts(Long attempts) {
		this.attempts = attempts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}


