package kaz.post.crmserver.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A user cache for registration.
 */
@Entity
@Table(name = "T_USERCACHE")
@SequenceGenerator(name = "id_gen", sequenceName = "usercache_seq", initialValue = 60000000, allocationSize = 1)
public class UserCacheEntity extends AbstractAuditingEntity {

	public static final int MAX_RETRY_COUNT = 3;

	@Size(max = 50)
	@Column(length = 50)
	private String login;

	@Size(max = 50)
	@Column(name = "mobile_number", length = 50)
	private String mobileNumber;

	@Email
	@Size(max = 100)
	@Column(length = 100)
	private String email;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;

	@Size(max = 5000)
	@Column(name = "json_data", length = 5000)
	private String jsonData;

	@Size(max = 2000)
	@Column(name = "json_organization", length = 2000)
	private String jsonOrganization;

	@Size(max = 2000)
	@Column(name = "json_services", length = 2000)
	private String jsonServices;

	@Column(name = "is_invite_sent")
	private Boolean isInviteSent;

	@Size(max = 40)
	@Column(name = "invite_token")
	private String inviteToken;

	@ColumnDefault("0")
	@Column(name = "sms_retry_count")
	private int smsRetryCount;

	@ColumnDefault("0")
	@Column(name = "resendCount")
	private int resendCount;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "block_time")
	private DateTime blockTime;

	public String getJsonServices() {
		return jsonServices;
	}

	public void setJsonServices(String jsonServices) {
		this.jsonServices = jsonServices;
	}

	public Boolean getInviteSent() {
		return isInviteSent;
	}

	public void setInviteSent(Boolean inviteSent) {
		isInviteSent = inviteSent;
	}

	public String getInviteToken() {
		return inviteToken;
	}

	public void setInviteToken(String inviteToken) {
		this.inviteToken = inviteToken;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public int getSmsRetryCount() {
		return smsRetryCount;
	}

	public void setSmsRetryCount(int smsRetryCount) {
		this.smsRetryCount = smsRetryCount;
	}

	public int getResendCount() {
		return resendCount;
	}

	public void setResendCount(int resend_count) {
		this.resendCount = resend_count;
	}

	public DateTime getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(DateTime blockTime) {
		this.blockTime = blockTime;
	}

	public String getJsonOrganization() {
		return jsonOrganization;
	}

	public void setJsonOrganization(String jsonOrganization) {
		this.jsonOrganization = jsonOrganization;
	}

	public boolean isExpired() {
		DateTime createDate = getCreatedDate();
		if (createDate == null) {
			return true;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(createDate.toDate());
		calendar.add(Calendar.HOUR, 1);
		Date hourLaterDate = calendar.getTime();
		return hourLaterDate.before(new Date());
	}

	public boolean isNotValidRetryCount() {
		return smsRetryCount >= MAX_RETRY_COUNT;
	}

	public boolean isUserBlocked() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, -1);
		return resendCount > MAX_RETRY_COUNT  && (blockTime == null || blockTime.isAfter(calendar.getTimeInMillis()));
	}

	@Override
	public String toString() {
		return "UserCacheEntity{" +
				"login='" + login + '\'' +
				", mobileNumber='" + mobileNumber + '\'' +
				", email='" + email + '\'' +
				", activationKey='" + activationKey + '\'' +
				", jsonData='" + jsonData + '\'' +
				", jsonOrganization='" + jsonOrganization + '\'' +
				", jsonServices='" + jsonServices + '\'' +
				", isInviteSent=" + isInviteSent +
				", inviteToken='" + inviteToken + '\'' +
				", smsRetryCount=" + smsRetryCount +
				", resendCount=" + resendCount +
				", blockTime=" + blockTime +
				'}';
	}
}
