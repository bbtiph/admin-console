package kaz.post.crmserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "T_PARCELLOG")
@SequenceGenerator(name = "id_gen", sequenceName = "parcellog_seq", initialValue = 60000000, allocationSize = 1)
public class ParcelLogEntity extends AbstractAuditingEntity {

	@Column(length = 50)
	private String author;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "type")
	private String type;

	@Column(name = "index")
	private String index;

	@Column(name = "prev_index")
	private String prevIndex;

	@Column(name = "prev_address")
	private String prevAddress;

	@JsonIgnore
	@Column(name = "activation_key")
	private String activationKey;

	@JsonIgnore
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "activation_key_sent_date")
	private DateTime activationKeySentDate;

	@Column(name = "activation_key_count")
	private Integer activationKeyCount;

	@Column(name = "activation_retry_count")
	private Integer activationRetryCount;

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private String activationKeyCheck;

	@Column(name = "address_index")
	private String addressIndex;

	@Column(name = "address_ats")
	private String addressAts;

	@Column(name = "address_description")
	private String addressDescription;

	@Column(name = "address_hours")
	private String addressHours;

	@Column(name = "home_address_index")
	private String homeAddressIndex;

	@Column(name = "home_address_city")
	private String homeAddressCity;

	@Column(name = "home_address_district")
	private String homeAddressDistrict;

	@Column(name = "home_address_street")
	private String homeAddressStreet;

	@Column(name = "home_address_house")
	private String homeAddressHouse;

	@Column(name = "home_address_flat")
	private String homeAddressFlat;

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private AbstractAddressEntity address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "iin")
	private String iin;

	@Column(name = "postamat")
	private Boolean postamat;

	@Column(name = "status")
	private String status;

	private Boolean sheduled;

	private Boolean sent;

	@Column(name = "payment_uid")
	private String paymentUid;

	@Column(name = "reference_id")
	private String referenceId;

	@Column(name = "payment_status")
	private PaymentStatus paymentStatus;

	@Column(name = "storagedays")
	private Integer storagedays;

	@Column(name = "storagepayment")
	private Integer storagepayment;

	@Column(name = "overdue_storagedays")
	private Integer overdueStoragedays;

	@Column(name = "colvir_payment_status")
	private PaymentStatus colvirPaymentStatus;

	@JsonIgnore
	@Column(name = "payment_data", length = 5000)
	private String paymentData;

	@Column(name = "payment_original_data", length = 5000)
	private String paymentOriginalData;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "lastshedule_date")
	private DateTime lastsheduleDate;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "sent_date")
	private DateTime sentDate;

	@JsonIgnore
	@Column(name = "send_error_data", length = 8000)
	private String sendErrorData;

	@JsonIgnore
	@Column(name = "payment_error_data", length = 8000)
	private String paymentErrorData;

	@JsonIgnore
	@Column(name = "colvir_error_data", length = 8000)
	private String colvirErrorData;

	@Column(name = "service_price")
	private Integer servicePrice;

	@Column(name = "telegram_sent")
	private Boolean telegramSent;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AbstractAddressEntity getAddress() {
		return address;
	}

	public void setAddress(AbstractAddressEntity address) {
		this.address = address;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getAddressIndex() {
		return addressIndex;
	}

	public void setAddressIndex(String addressIndex) {
		this.addressIndex = addressIndex;
	}

	public String getAddressAts() {
		return addressAts;
	}

	public void setAddressAts(String addressAts) {
		this.addressAts = addressAts;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getAddressHours() {
		return addressHours;
	}

	public void setAddressHours(String addressHours) {
		this.addressHours = addressHours;
	}

	public String getHomeAddressIndex() {
		return homeAddressIndex;
	}

	public void setHomeAddressIndex(String homeAddressIndex) {
		this.homeAddressIndex = homeAddressIndex;
	}

	public String getHomeAddressCity() {
		return homeAddressCity;
	}

	public void setHomeAddressCity(String homeAddressCity) {
		this.homeAddressCity = homeAddressCity;
	}

	public String getHomeAddressDistrict() {
		return homeAddressDistrict;
	}

	public void setHomeAddressDistrict(String homeAddressDistrict) {
		this.homeAddressDistrict = homeAddressDistrict;
	}

	public String getHomeAddressStreet() {
		return homeAddressStreet;
	}

	public void setHomeAddressStreet(String homeAddressStreet) {
		this.homeAddressStreet = homeAddressStreet;
	}

	public String getHomeAddressHouse() {
		return homeAddressHouse;
	}

	public void setHomeAddressHouse(String homeAddressHouse) {
		this.homeAddressHouse = homeAddressHouse;
	}

	public String getHomeAddressFlat() {
		return homeAddressFlat;
	}

	public void setHomeAddressFlat(String homeAddressFlat) {
		this.homeAddressFlat = homeAddressFlat;
	}

	public String getActivationKeyCheck() {
		return activationKeyCheck;
	}

	public void setActivationKeyCheck(String activationKeyCheck) {
		this.activationKeyCheck = activationKeyCheck;
	}

	public String getIin() {
		return iin;
	}

	public void setIin(String iin) {
		this.iin = iin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getSheduled() {
		return sheduled;
	}

	public void setSheduled(Boolean sheduled) {
		this.sheduled = sheduled;
	}

	public DateTime getLastsheduleDate() {
		return lastsheduleDate;
	}

	public void setLastsheduleDate(DateTime lastsheduleDate) {
		this.lastsheduleDate = lastsheduleDate;
	}

	public Boolean getSent() {
		return sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

	public DateTime getSentDate() {
		return sentDate;
	}

	public void setSentDate(DateTime sentDate) {
		this.sentDate = sentDate;
	}

	public String getPrevIndex() {
		return prevIndex;
	}

	public void setPrevIndex(String prevIndex) {
		this.prevIndex = prevIndex;
	}

	public String getPrevAddress() {
		return prevAddress;
	}

	public void setPrevAddress(String prevAddress) {
		this.prevAddress = prevAddress;
	}

	public Boolean getPostamat() {
		return postamat;
	}

	public void setPostamat(Boolean postamat) {
		this.postamat = postamat;
	}

	public String getPaymentUid() {
		return paymentUid;
	}

	public void setPaymentUid(String paymentUid) {
		this.paymentUid = paymentUid;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(String paymentData) {
		this.paymentData = paymentData;
	}

	public String getPaymentOriginalData() {
		return paymentOriginalData;
	}

	public void setPaymentOriginalData(String paymentOriginalData) {
		this.paymentOriginalData = paymentOriginalData;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public PaymentStatus getColvirPaymentStatus() {
		return colvirPaymentStatus;
	}

	public void setColvirPaymentStatus(PaymentStatus colvirPaymentStatus) {
		this.colvirPaymentStatus = colvirPaymentStatus;
	}

	public DateTime getActivationKeySentDate() {
		return activationKeySentDate;
	}

	public void setActivationKeySentDate(DateTime activationKeySentDate) {
		this.activationKeySentDate = activationKeySentDate;
	}

	public Integer getActivationKeyCount() {
		return activationKeyCount;
	}

	public void setActivationKeyCount(Integer activationKeyCount) {
		this.activationKeyCount = activationKeyCount;
	}

	public Integer getActivationRetryCount() {
		return activationRetryCount;
	}

	public void setActivationRetryCount(Integer activationRetryCount) {
		this.activationRetryCount = activationRetryCount;
	}

	public String getSendErrorData() {
		return sendErrorData;
	}

	public void setSendErrorData(String sendErrorData) {
		this.sendErrorData = sendErrorData;
	}

	public String getPaymentErrorData() {
		return paymentErrorData;
	}

	public void setPaymentErrorData(String paymentErrorData) {
		this.paymentErrorData = paymentErrorData;
	}

	public String getColvirErrorData() {
		return colvirErrorData;
	}

	public void setColvirErrorData(String colvirErrorData) {
		this.colvirErrorData = colvirErrorData;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Integer getStoragedays() {
		return storagedays;
	}

	public void setStoragedays(Integer storagedays) {
		this.storagedays = storagedays;
	}

	public Integer getStoragepayment() {
		return storagepayment;
	}

	public void setStoragepayment(Integer storagepayment) {
		this.storagepayment = storagepayment;
	}

	public Integer getOverdueStoragedays() {
		return overdueStoragedays;
	}

	public void setOverdueStoragedays(Integer overdueStoragedays) {
		this.overdueStoragedays = overdueStoragedays;
	}

	public Integer getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Boolean getTelegramSent() {
		return telegramSent;
	}

	public void setTelegramSent(Boolean telegramSent) {
		this.telegramSent = telegramSent;
	}
}
