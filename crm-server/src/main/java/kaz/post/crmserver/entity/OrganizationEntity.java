package kaz.post.crmserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "T_ORGANIZATION")
@SequenceGenerator(name = "id_gen", sequenceName = "org_seq", initialValue = 60000000, allocationSize = 1)
public class OrganizationEntity extends AbstractUserEntity {

	@NotNull
	@Size(min = 12, max = 12)
	@Column(name = "bin", length = 12, unique = true)
	private String bin;

	@Size(max = 500)
	@Column(name = "short_name", length = 500)
	private String shortName;

	@Size(max = 500)
	@Column(name = "long_name", length = 500)
	private String longName;

	@Size(max = 500)
	@Column(name = "head_fullname", length = 500)
	private String headFullname;

	@Size(max = 500)
	@Column(name = "responsible_fullname", length = 500)
	private String responsibleFullname;

	@Size(max = 50)
	@Column(name = "contract_number", length = 50)
	private String contractNumber;

	@Transient
	@JsonSerialize
	private List<OrganizationContractEntity> contracts;

	@Transient
	@JsonSerialize
	private OrganizationFactAddressEntity factAddress;

	@Transient
	@JsonSerialize
	private OrganizationLegalAddressEntity legalAddress;

	@Size(max = 500)
	@Column(name = "phone_number", length = 500)
	private String phoneNumber;

	@Size(max = 500)
	@Column(name = "fax_number", length = 500)
	private String faxNumber;

	@Column(name = "reg_date")
	private Date regDate;

	@Email
	@Size(max = 100)
	@Column(length = 100)
	private String email;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;

	@Lob
	@Column(name = "photo")
	private byte[] photo;

	@Column
	private Boolean confirmed;

	@Column(name = "ecp")
	private Boolean ecp;

	@Column(name = "activated")
	private Boolean activated;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "T_ORGANIZATION_AUTHORITY",
		joinColumns = {@JoinColumn(name = "organization_id", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")},
		uniqueConstraints = {@UniqueConstraint(name = "org_authority_uk" , columnNames = {"organization_id", "authority_name"})})
	private List<AuthorityEntity> authorities = new ArrayList<>();

//	@JsonIgnore
//	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
//	private List<AddressEntity> addressList;

	@JsonIgnore
	@OneToMany(mappedBy = "organization",cascade = CascadeType.MERGE, orphanRemoval = true,  fetch = FetchType.LAZY)
	private List<OrganizationFactAddressEntity> addressList = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		OrganizationEntity org = (OrganizationEntity) o;

		if (!bin.equals(org.bin)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return bin.hashCode();
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public List<OrganizationContractEntity> getContracts() {
		return contracts;
	}

	public void setContracts(List<OrganizationContractEntity> contracts) {
		this.contracts = contracts;
	}


	public Boolean getEcp() {
		return ecp;
	}

	public void setEcp(Boolean ecp) {
		this.ecp = ecp;
	}

	public List<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	public List<OrganizationFactAddressEntity> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<OrganizationFactAddressEntity> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "OrganizationEntity{" +
			"bin='" + bin + '\'' +
			", login='" + super.getLogin() + '\'' +
			", mobileNumber='" + super.getMobileNumber() + '\'' +
			", email='" + email + '\'' +
			", regDate=" + regDate +
			", shortName='" + shortName + '\'' +
			", longName='" + longName + '\'' +
			", headFullname='" + headFullname + '\'' +
			", responsibleFullname='" + responsibleFullname + '\'' +
			", contractNumber='" + contractNumber + '\'' +
			", contracts=" + contracts +
			", factAddress=" + factAddress +
			", legalAddress=" + legalAddress +
			", phoneNumber='" + phoneNumber + '\'' +
			", faxNumber='" + faxNumber + '\'' +
			", activationKey='" + activationKey + '\'' +
			", photo=" + Arrays.toString(photo) +
			", confirmed=" + confirmed +
			", ecp=" + ecp +
			", activated=" + activated +
			", authorities=" + authorities +
			", addressList=" + addressList +
			'}';
	}
}
