package kaz.post.crmserver.dto;

import kaz.post.crmserver.entity.OrganizationEntity;
import kaz.post.crmserver.entity.UserAddressEntity;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class UserDTO extends Dto {

	List<OrganizationEntity> organizations;

	@Size(max = 50, message = "Max length of first name must be 50 symbols.")
	private String firstName;
	@Size(max = 50, message = "Max length of last name must be 50 symbols.")
	private String lastName;
	@Size(max = 50, message = "Max length of parent(middle) name must be 50 symbols.")
	private String middleName;
	private Date birthDate;

	private String registrationCacheUid;
	private Boolean hasTelegramIntegration;
	private String telegramUUID;
	private Boolean hasBCard;
	private String bCardURL;
	@Email
//	@Size(max = 100, message = "Max length of email must be 100 symbols.")
	private String email;
	private UserAddressEntity address;
	@Size(min = 2, max = 5)
	private String langKey;
	private Boolean hasAvatar;
	private Boolean confirmed;
	private List<String> roles;
	private String type;
	private String token;
	private String position;
	private Boolean disablePush;
	private Boolean contract;
	private Boolean walletConfirmedOffer;
	private Boolean enabledMobileSecurity;
	private String captcha;
	private String employeeNumber;
	private Boolean isFl;
	private String userRole;
	private DateTime createdDate;
	private Long id;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UserDTO() {
	}

	public UserDTO(String login, String password, String firstName, String lastName, String middleName, Date birthDate, String iin, String mobileNumber, String langKey, Boolean confirmed,
                   List<String> roles, String position, Boolean disablePush, Boolean contract, Boolean walletConfirmedOffer, Boolean enabledMobileSecurity, String employeeNumber, String userRole, DateTime createdDate, Long id) {
		super(login, iin, mobileNumber, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.birthDate = birthDate;
		this.langKey = langKey;
		this.roles = roles;
		this.confirmed = confirmed;
		this.position = position;
		this.disablePush = disablePush;
		this.contract = contract;
		this.walletConfirmedOffer = walletConfirmedOffer;
		this.enabledMobileSecurity = enabledMobileSecurity;
		this.employeeNumber = employeeNumber;
		this.userRole = userRole;
		this.createdDate = createdDate;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getPassword() {
		return super.getPassword();
	}

	public void setPassword(String password) {
		super.setPassword(password);
	}

	public String getLogin() {
		return super.getLogin();
	}

	public void setLogin(String login) {
		super.setLogin(login);
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

	public String getEmail() {
		return email;
	}

	public String getLangKey() {
		return langKey;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getIin() {
		return super.getIin();
	}

	public void setIin(String iin) {
		super.setIin(iin);
	}

	public String getMobileNumber() {
		return super.getMobileNumber();
	}

	public void setMobileNumber(String mobileNumber) {
		super.setMobileNumber(mobileNumber);
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public UserAddressEntity getAddress() {
		return address;
	}

	public void setAddress(UserAddressEntity address) {
		this.address = address;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Boolean getHasAvatar() {
		return hasAvatar;
	}

	public void setHasAvatar(Boolean hasAvatar) {
		this.hasAvatar = hasAvatar;
	}

	public List<OrganizationEntity> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<OrganizationEntity> organizations) {
		this.organizations = organizations;
	}

	public String getRegistrationCacheUid() {
		return registrationCacheUid;
	}

	public void setRegistrationCacheUid(String registrationCacheUid) {
		this.registrationCacheUid = registrationCacheUid;
	}

	public String getTelegramUUID() {
		return telegramUUID;
	}

	public void setTelegramUUID(String telegramUUID) {
		this.telegramUUID = telegramUUID;
	}

	public Boolean getHasTelegramIntegration() {
		return hasTelegramIntegration;
	}

	public void setHasTelegramIntegration(Boolean hasTelegramIntegration) {
		this.hasTelegramIntegration = hasTelegramIntegration;
	}

	public Boolean getHasBCard() {
		return hasBCard;
	}

	public void setHasBCard(Boolean hasBCard) {
		this.hasBCard = hasBCard;
	}

	public String getbCardURL() {
		return bCardURL;
	}

	public void setbCardURL(String bCardURL) {
		this.bCardURL = bCardURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Boolean getDisablePush() {
		return disablePush;
	}

	public void setDisablePush(Boolean disablePush) {
		this.disablePush = disablePush;
	}

	public Boolean isContract() {
		return contract;
	}

	public void setContract(Boolean contract) {
		this.contract = contract;
	}

	public Boolean getWalletConfirmedOffer() {
		return walletConfirmedOffer;
	}

	public void setWalletConfirmedOffer(Boolean walletConfirmedOffer) {
		this.walletConfirmedOffer = walletConfirmedOffer;
	}

	public Boolean getEnabledMobileSecurity() {
		return enabledMobileSecurity;
	}

	public void setEnabledMobileSecurity(Boolean enabledMobileSecurity) {
		this.enabledMobileSecurity = enabledMobileSecurity;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Boolean getFl() {
		return isFl;
	}

	public void setFl(Boolean fl) {
		isFl = fl;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
			"organizations=" + organizations +
			", login='" + super.getLogin() + '\'' +
			", password='" + super.getPassword() + '\'' +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", middleName='" + middleName + '\'' +
			", birthDate=" + birthDate +
			", iin='" + super.getIin() + '\'' +
			", mobileNumber='" + super.getMobileNumber() + '\'' +
			", registrationCacheUid='" + registrationCacheUid + '\'' +
			", hasTelegramIntegration=" + hasTelegramIntegration +
			", telegramUUID='" + telegramUUID + '\'' +
			", hasBCard=" + hasBCard +
			", bCardURL='" + bCardURL + '\'' +
			", email='" + email + '\'' +
			", address=" + address +
			", langKey='" + langKey + '\'' +
			", hasAvatar=" + hasAvatar +
			", confirmed=" + confirmed +
			", roles=" + roles +
			", type='" + type + '\'' +
			", token='" + token + '\'' +
			", position='" + position + '\'' +
			", disablePush=" + disablePush +
			", contract=" + contract +
			", walletConfirmedOffer=" + walletConfirmedOffer +
			", enabledMobileSecurity=" + enabledMobileSecurity +
			", captcha='" + captcha + '\'' +
			", employeeNumber='" + employeeNumber + '\'' +
			'}';
	}
}
