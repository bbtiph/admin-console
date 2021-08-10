package kaz.post.crmserver.entity.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kaz.post.crmserver.entity.AbstractUserEntity;
import kaz.post.crmserver.entity.AuthorityEntity;
import kaz.post.crmserver.entity.UserAddressEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "T_USER", indexes = {@Index(name = "userMobIndex", columnList = "mobile_number")})
@SequenceGenerator(name = "id_gen", sequenceName = "user_seq", initialValue = 60000000, allocationSize = 1)
public class UserEntity extends AbstractUserEntity implements Serializable {

	@Size(max = 12, message = "ЖСН ұзындығы 12 символдан тұруы керек./Длина ИИН должна составлять 12 символов./Length of IIN must be 12 symbols.")
	@Column(name = "iin", length = 12, unique = true)
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

	@Column(name = "telegram_user_id")
	private String telegramUserId;

	@Column(name = "facebook_user_id")
	private String facebookUserId;

	@Column(name = "has_telegram_integration")
	private Boolean hasTelegramIntegration;

	@Column(name = "has_facebookintegration")
	private Boolean hasFacebookIntegration;

	@Column(name = "processing_confirmed_date")
	private Date processingConfirmedDate;

	@Column(name = "processing_client_id")
	private String processingClientId;

	@Email
	@Size(max = 100)
	@Column(length = 100)
	private String email;

	@Column(nullable = false)
	private boolean activated = false;

	@Size(min = 2, max = 5)
	@Column(name = "lang_key", length = 5)
	private String langKey;

	@Size(min = 2, max = 10)
	@Column(name = "government_notification", length = 10)
	private String governmentNotification;

	@Size(min = 2, max = 10)
	@Column(name = "company_notification", length = 10)
	private String companyNotification;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;

	@Lob
	@Column(name = "photo")
	private byte[] photo;

	@Column(name = "gender")
	private Boolean gender;

	@Column
	private Boolean confirmed;

	@Column(name = "signature", length = 5000)
	private String signature;

	@Column(name = "position", length = 600)
	private String position;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "T_USER_AUTHORITY",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
	private Set<AuthorityEntity> authorities = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<UserAddressEntity> addressList = new ArrayList<>();

	@Column
	private Boolean disablePush;

	@Column(name = "has_contract")
	private Boolean contract;

	@Column(name = "is_wallet_confirmed_offer")
	private Boolean walletConfirmedOffer;

	@Column(name = "wallet_sms_code")
	private Integer walletSmsCode;

	@Column(name = "is_enabled_mobile_security")
	private Boolean enabledMobileSecurity;

	@Column(name = "bio_hash", length = 600)
	private String bioHash;

	@Column(name = "employee_number", length = 20)
	private String employeeNumber;

	@JsonIgnore
	public List<UserAddressEntity> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<UserAddressEntity> addressList) {
		this.addressList = addressList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		UserEntity user = (UserEntity) o;

		if (!getLogin().equals(user.getLogin())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return getLogin().hashCode();
	}

	@Override
	public String toString() {
		return "User{" +
				"login='" + getLogin() + '\'' +
				", iin='" + iin + '\'' +
				", mobileNumber='" + getMobileNumber() + '\'' +
				", password='" + getPassword() + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", activated='" + activated + '\'' +
				", langKey='" + langKey + '\'' +
				", activationKey='" + activationKey + '\'' +
				", iostoken='" + getIosToken() + '\'' +
				", androitoken='" + getAndroidToken() + '\'' +
				"}";
	}
}


