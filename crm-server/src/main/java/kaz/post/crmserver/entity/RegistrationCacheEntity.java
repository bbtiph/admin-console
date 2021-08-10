package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A registration cache for Egov SSO.
 */
@Entity
@Table(name = "T_REGISTRATIONCACHE")
@SequenceGenerator(name = "id_gen", sequenceName = "reg_cache_seq", initialValue = 60000000, allocationSize = 1)
public class RegistrationCacheEntity extends AbstractAuditingEntity {

	@Size(max = 200)
	@Column(length = 200)
	private String uid;

	@Size(max = 5000)
	@Column(name = "token", length = 5000)
	private String token;

	@Size(max = 12)
	@Column(name = "iin", length = 12)
	private String iin;

	@Size(max = 50)
	@Column(name = "first_name", length = 50)
	private String firstName;

	@Size(max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;

	@Size(max = 50)
	@Column(name = "middle_name", length = 50)
	private String middleName;

	@Column(name = "birth_date")
	private Date birthDate;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIin() {
		return iin;
	}

	public void setIin(String iin) {
		this.iin = iin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "RegistrationCache{" +
			"uid='" + uid + '\'' +
			", iin='" + iin + '\'' +
			", token='" + token + '\'' +
			"}";
	}

}
