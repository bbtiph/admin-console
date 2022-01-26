package kaz.post.crmserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "T_BATCH_PACKAGE")
@SequenceGenerator(name = "id_gen", sequenceName = "batch_package_seq", initialValue = 60000000, allocationSize = 1)
public class BatchPackageEntity extends AbstractAuditingEntity {

	@Column(name = "sender_name", length = 150)
	private String senderName;

	@Column(name = "sender_iin_bin")
	private String senderIinBin;

	@Column(name = "sender_phone", length = 150)
	private String senderPhone;

	@Column(name = "sender_email", length = 150)
	private String senderEmail;

	@Column(name = "sender_address", length = 350)
	private String senderAddress;

	@Column(name = "sender_index_old", length = 50)
	private String senderIndexOld;

	@Column(name = "sender_index_new", length = 50)
	private String senderIndexNew;

	@Column(name = "sender_country")
	private String senderCountry;

	@Column(name = "sender_ats")
	private String senderAts;

	@Column(name = "sender_geonim")
	private String senderGeonim;

	@Column(name = "sender_building")
	private String senderBuilding;

	@Column(name = "sender_flat")
	private String senderFlat;

	@Column(name = "recipient_name", length = 150)
	private String recipientName;

	@Column(name = "recipient_iin_bin")
	private String recipientIinBin;

	@Column(name = "recipient_phone", length = 150)
	private String recipientPhone;

	@Column(name = "recipient_address", length = 350)
	private String recipientAddress;

	@Column(name = "recipient_index_old", length = 50)
	private String recipientIndexOld;

	@Column(name = "recipient_index_new", length = 50)
	private String recipientIndexNew;

	@Column(name = "recipient_country")
	private String recipientCountry;

	@Column(name = "recipient_ats")
	private String recipientAts;

	@Column(name = "recipient_geonim")
	private String recipientGeonim;

	@Column(name = "recipient_building")
	private String recipientBuilding;

	@Column(name = "recipient_flat")
	private String recipientFlat;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "sum_of_declared_value")
	private String sumOfDeclaredValue;

	@Column(name = "sum_of_cash_on_delivery")
	private String sumOfCashOnDelivery;

	@Column(name = "send_type")
	private String sendType;

	@JsonIgnore
	@Lob
//	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "addrLetter", columnDefinition = "TEXT")
	private String addrLetter;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderIinBin() {
		return senderIinBin;
	}

	public void setSenderIinBin(String senderIinBin) {
		this.senderIinBin = senderIinBin;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderIndexOld() {
		return senderIndexOld;
	}

	public void setSenderIndexOld(String senderIndexOld) {
		this.senderIndexOld = senderIndexOld;
	}

	public String getSenderIndexNew() {
		return senderIndexNew;
	}

	public void setSenderIndexNew(String senderIndexNew) {
		this.senderIndexNew = senderIndexNew;
	}

	public String getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getSenderAts() {
		return senderAts;
	}

	public void setSenderAts(String senderAts) {
		this.senderAts = senderAts;
	}

	public String getSenderGeonim() {
		return senderGeonim;
	}

	public void setSenderGeonim(String senderGeonim) {
		this.senderGeonim = senderGeonim;
	}

	public String getSenderBuilding() {
		return senderBuilding;
	}

	public void setSenderBuilding(String senderBuilding) {
		this.senderBuilding = senderBuilding;
	}

	public String getSenderFlat() {
		return senderFlat;
	}

	public void setSenderFlat(String senderFlat) {
		this.senderFlat = senderFlat;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientIinBin() {
		return recipientIinBin;
	}

	public void setRecipientIinBin(String recipientIinBin) {
		this.recipientIinBin = recipientIinBin;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientIndexOld() {
		return recipientIndexOld;
	}

	public void setRecipientIndexOld(String recipientIndexOld) {
		this.recipientIndexOld = recipientIndexOld;
	}

	public String getRecipientIndexNew() {
		return recipientIndexNew;
	}

	public void setRecipientIndexNew(String recipientIndexNew) {
		this.recipientIndexNew = recipientIndexNew;
	}

	public String getRecipientCountry() {
		return recipientCountry;
	}

	public void setRecipientCountry(String recipientCountry) {
		this.recipientCountry = recipientCountry;
	}

	public String getRecipientAts() {
		return recipientAts;
	}

	public void setRecipientAts(String recipientAts) {
		this.recipientAts = recipientAts;
	}

	public String getRecipientGeonim() {
		return recipientGeonim;
	}

	public void setRecipientGeonim(String recipientGeonim) {
		this.recipientGeonim = recipientGeonim;
	}

	public String getRecipientBuilding() {
		return recipientBuilding;
	}

	public void setRecipientBuilding(String recipientBuilding) {
		this.recipientBuilding = recipientBuilding;
	}

	public String getRecipientFlat() {
		return recipientFlat;
	}

	public void setRecipientFlat(String recipientFlat) {
		this.recipientFlat = recipientFlat;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSumOfDeclaredValue() {
		return sumOfDeclaredValue;
	}

	public void setSumOfDeclaredValue(String sumOfDeclaredValue) {
		this.sumOfDeclaredValue = sumOfDeclaredValue;
	}

	public String getSumOfCashOnDelivery() {
		return sumOfCashOnDelivery;
	}

	public void setSumOfCashOnDelivery(String sumOfCashOnDelivery) {
		this.sumOfCashOnDelivery = sumOfCashOnDelivery;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getAddrLetter() {
		return addrLetter;
	}

	public void setAddrLetter(String addrLetter) {
		this.addrLetter = addrLetter;
	}
}
