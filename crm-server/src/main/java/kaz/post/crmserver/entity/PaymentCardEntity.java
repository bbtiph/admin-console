package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_PAYMENT_CARD")
@SequenceGenerator(name = "id_gen", sequenceName = "hmail_seq", initialValue = 60000000, allocationSize = 1)
public class PaymentCardEntity extends AbstractAuditingEntity {

	@Column(name = "card_type", length = 2000)
	private String cardType;

	@Column(name = "currency", length = 2000)
	private String currency;

	@Column(name = "delivery_index", length = 2000)
	private String deliveryIndex;

	@Column(name = "deliver_place", length = 5000)
	private String deliveryPlace;

	@Column(name = "doc_num", length = 500)
	private String docNum;

	@Column(name = "doc_type", length = 500)
	private String docType;

	@Column(name = "fname_lat", length = 2000)
	private String fNameLat;

	@Column(name = "first_name", length = 2000)
	private String firstName;

	@Column(name = "lname_lat", length = 2000)
	private String lNameLat;

	@Column(name = "last_name", length = 2000)
	private String lastName;

	@Column(name = "iin", length = 100)
	private String iin;

	@Column(name = "middle_name", length = 2000)
	private String middleName;

	@Column(name = "mobile_number", length = 2000)
	private String mobileNumber;

	@Column(name = "pass_phrase", length = 2000)
	private String passPhrase;

	@Column(name = "product_type", length = 500)
	private String productType;

	@Column(name = "file_org_doc_name", length = 5000)
	private String fileOrgDocName;

	@Column(name = "file_org_doc", length = 10000)
	private String fileOrgDoc;

	@Column(name = "work_place", length = 5000)
	private String workPlace;

	@Column(name = "status")
	private boolean status;

	@Column(name = "resident", length = 500)
	private String resident;

	@Override
	public String toString(){
		return "PaymentCardEntity{"
			+ "id=" +getId()+
			", cardType='" + cardType + '\'' +
			", currency='" + currency + '\'' +
			", deliveryIndex='" + deliveryIndex + '\'' +
			", deliveryPlace='" + deliveryPlace + '\'' +
			", docNum='" + docNum + '\'' +
			", docType='" + docType + '\'' +
			", fNameLat='" + fNameLat + '\'' +
			", firstName='" + firstName + '\'' +
			", lNameLat='" + lNameLat + '\'' +
			", lastName='" + lastName + '\'' +
			", iin='" + iin + '\'' +
			", middleName='" + middleName + '\'' +
			", mobileNumber='" + mobileNumber + '\'' +
			", passPhrase='" + passPhrase + '\'' +
			", productType='" + productType + '\'' +
			", fileOrgDocName='" + fileOrgDocName + '\'' +
			", fileOrgDoc='" + fileOrgDoc + '\'' +
			", workPlace='" + workPlace + '\'' +
			", status='" + status + '\'' +
			", resident='" + resident + '\'' +
			"}";
	}

	public String getCardType(){
		return cardType;
	}

	public  void setCardType(String cardType){
		this.cardType = cardType;
	}

	public String getCurrency(){
		return currency;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getDeliveryIndex(){
		return deliveryIndex;
	}

	public void setDeliveryIndex(String deliveryIndex){
		this.deliveryIndex = deliveryIndex;
	}

	public String getDeliveryPlace(){
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace){
		this.deliveryPlace = deliveryPlace;
	}

	public String getDocNum(){
		return docNum;
	}

	public void setDocNum(String docNum){
		this.docNum = docNum;
	}

	public String getDocType(){
		return docType;
	}

	public void setDocType(String docType){
		this.docType = docType;
	}

	public String getfNameLat(){
		return fNameLat;
	}

	public void setfNameLat(String fNameLat){
		this.fNameLat = fNameLat;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getIin(){
		return iin;
	}

	public void setIin(String iin){
		this.iin = iin;
	}

	public String getlNameLat(){
		return lNameLat;
	}

	public void setlNameLat(String lNameLat){
		this.lNameLat = lNameLat;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getMiddleName(){
		return middleName;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getPassPhrase(){
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase){
		this.passPhrase = passPhrase;
	}

	public String getProductType(){
		return productType;
	}

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getFileOrgDocName(){
		return fileOrgDocName;
	}

	public void setFileOrgDocName(String fileOrgDocName){
		this.fileOrgDocName = fileOrgDocName;
	}

	public String getFileOrgDoc(){
		return fileOrgDoc;
	}

	public void setFileOrgDoc(String fileOrgDocName){
		this.fileOrgDoc = fileOrgDoc;
	}

	public Boolean getStatus(){
		return  status;
	}

	public void  setStatus(Boolean status){
		this.status = status;
	}

	public  String getWorkPlace(){
		return workPlace;
	}

	public void setWorkPlace(String workPlace){
		this.workPlace = workPlace;
	}

	public String getResident(){
		return resident;
	}

	public  void setResident(String resident){
		this.resident= resident;
	}
}
