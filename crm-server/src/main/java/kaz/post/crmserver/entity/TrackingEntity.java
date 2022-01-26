package kaz.post.crmserver.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

/**
 * Created by user on 01.07.2015.
 */

@Entity
@Table(name = "T_TRACKING", indexes = {@Index(name = "loginIndex", columnList = "login")})
@SequenceGenerator(name = "id_gen", sequenceName = "tracking_seq", initialValue = 60000000, allocationSize = 1)
public class TrackingEntity extends AbstractEntity {


	@Column
	private String number;

	@Column
	private String name;

	@Column
	private String login;

	private String lastStatus;

	@CreatedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "created_date")
	private DateTime createdDate = DateTime.now(); //can't extend AbstractAuditingEntity because created_date is not null which conflicts with old values without date (date=null)

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}
}
