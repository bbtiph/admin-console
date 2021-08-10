package kaz.post.crmserver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * An organization contracts info.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_ORGANIZATION_CONTRACT")
@SequenceGenerator(name = "id_gen", sequenceName = "org_contract_seq", initialValue = 60000000, allocationSize = 1)
public class OrganizationContractEntity extends AbstractEntity implements Serializable {


	@Column(name = "organization_id")
	private Long organizationId;

	@Column(name = "organization_bin", length = 50)
	private String organizationBin;

	@Column(name = "contract_number")
	private String contractNumber;

	@Column(name = "contract_id")
	private String contractId;

	@Column(name = "contract_index")
	private String contractIndex;

	@Column(name = "contract_date")
	private Date contractDate;

	@Column(name = "contract_department")
	private String contractDepartment;

	@Column(name = "tariff_category_code")
	private String tariffCategoryCode;

	@Column(name = "tariff_category_name")
	private String tariffCategoryName;

	@Column(name = "payment_type_code")
	private String paymentTypeCode;

	@Column(name = "payment_type_name")
	private String paymentTypeName;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private ServiceEntity service;

	@Column(name = "api_token")
	private String apiToken;

	@Column(name = "enabled")
	private Boolean enabled;

	public Long getOrganizationId() {
		return organizationId;
	}

	public OrganizationContractEntity setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
		return this;
	}

	public String getOrganizationBin() {
		return organizationBin;
	}

	public OrganizationContractEntity setOrganizationBin(String organizationBin) {
		this.organizationBin = organizationBin;
		return this;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public OrganizationContractEntity setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
		return this;
	}

	public String getContractId() {
		return contractId;
	}

	public OrganizationContractEntity setContractId(String contractId) {
		this.contractId = contractId;
		return this;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public OrganizationContractEntity setContractDate(Date contractDate) {
		this.contractDate = contractDate;
		return this;
	}

	public String getContractDepartment() {
		return contractDepartment;
	}

	public OrganizationContractEntity setContractDepartment(String contractDepartment) {
		this.contractDepartment = contractDepartment;
		return this;
	}

	public String getTariffCategoryCode() {
		return tariffCategoryCode;
	}

	public OrganizationContractEntity setTariffCategoryCode(String tariffCategoryCode) {
		this.tariffCategoryCode = tariffCategoryCode;
		return this;
	}

	public String getTariffCategoryName() {
		return tariffCategoryName;
	}

	public OrganizationContractEntity setTariffCategoryName(String tariffCategoryName) {
		this.tariffCategoryName = tariffCategoryName;
		return this;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public OrganizationContractEntity setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
		return this;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public OrganizationContractEntity setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
		return this;
	}

	public String getApiToken() {
		return apiToken;
	}

	public OrganizationContractEntity setApiToken(String apiToken) {
		this.apiToken = apiToken;
		return this;
	}

	public ServiceEntity getService() {
		return service;
	}

	public OrganizationContractEntity setService(ServiceEntity service) {
		this.service = service;
		return this;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public OrganizationContractEntity setEnabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public String getContractIndex() {
		return contractIndex;
	}

	public void setContractIndex(String contractIndex) {
		this.contractIndex = contractIndex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrganizationContractEntity that = (OrganizationContractEntity) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		if (organizationId != null ? !organizationId.equals(that.organizationId) : that.organizationId != null)
			return false;
		if (organizationBin != null ? !organizationBin.equals(that.organizationBin) : that.organizationBin != null)
			return false;
		if (contractNumber != null ? !contractNumber.equals(that.contractNumber) : that.contractNumber != null)
			return false;
		if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
		if (contractDate != null ? !contractDate.equals(that.contractDate) : that.contractDate != null) return false;
		if (contractDepartment != null ? !contractDepartment.equals(that.contractDepartment) : that.contractDepartment != null)
			return false;
		if (tariffCategoryCode != null ? !tariffCategoryCode.equals(that.tariffCategoryCode) : that.tariffCategoryCode != null)
			return false;
		if (tariffCategoryName != null ? !tariffCategoryName.equals(that.tariffCategoryName) : that.tariffCategoryName != null)
			return false;
		if (paymentTypeCode != null ? !paymentTypeCode.equals(that.paymentTypeCode) : that.paymentTypeCode != null)
			return false;
		if (paymentTypeName != null ? !paymentTypeName.equals(that.paymentTypeName) : that.paymentTypeName != null)
			return false;
		if (apiToken != null ? !apiToken.equals(that.apiToken) : that.apiToken != null) return false;
		if (service != null ? !service.equals(that.service) : that.service != null) return false;
		return enabled != null ? enabled.equals(that.enabled) : that.enabled == null;

	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (organizationId != null ? organizationId.hashCode() : 0);
		result = 31 * result + (organizationBin != null ? organizationBin.hashCode() : 0);
		result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
		result = 31 * result + (contractId != null ? contractId.hashCode() : 0);
		result = 31 * result + (contractDate != null ? contractDate.hashCode() : 0);
		result = 31 * result + (contractDepartment != null ? contractDepartment.hashCode() : 0);
		result = 31 * result + (tariffCategoryCode != null ? tariffCategoryCode.hashCode() : 0);
		result = 31 * result + (tariffCategoryName != null ? tariffCategoryName.hashCode() : 0);
		result = 31 * result + (paymentTypeCode != null ? paymentTypeCode.hashCode() : 0);
		result = 31 * result + (paymentTypeName != null ? paymentTypeName.hashCode() : 0);
		result = 31 * result + (apiToken != null ? apiToken.hashCode() : 0);
		result = 31 * result + (service != null ? service.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "OrganizationContract{" +
			"id=" + getId() +
			", organizationId=" + organizationId +
			", organizationBin='" + organizationBin + '\'' +
			", contractNumber='" + contractNumber + '\'' +
			", contractId='" + contractId + '\'' +
			", contractDate=" + contractDate +
			", contractDepartment='" + contractDepartment + '\'' +
			", tariffCategoryCode='" + tariffCategoryCode + '\'' +
			", tariffCategoryName='" + tariffCategoryName + '\'' +
			", paymentTypeCode='" + paymentTypeCode + '\'' +
			", paymentTypeName='" + paymentTypeName + '\'' +
			", apiToken='" + apiToken + '\'' +
			", service=" + service +
			", enabled=" + enabled +
			'}';
	}
}
