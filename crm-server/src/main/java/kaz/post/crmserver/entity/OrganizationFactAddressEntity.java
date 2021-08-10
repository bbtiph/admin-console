package kaz.post.crmserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * An organization fact Address.
 */
@Entity
@Table(name = "T_ORGANIZATION_FACTADDRESS")
@SequenceGenerator(name = "id_gen", sequenceName = "org_fact_address_seq", initialValue = 60000000, allocationSize = 1)
public class OrganizationFactAddressEntity extends AbstractAddressEntity {


//	@Column(name = "organization_id")
//	private Long organizationId;

	@Column(name = "organization_bin", length = 50)
	private String organizationBin;


	@ManyToOne
	@JoinColumn(name = "organization_id")
	public OrganizationEntity organization;

//	//public Long getOrganizationId() {
//		return organizationId;
//	}
//
//	public void setOrganizationId(Long organizationId) {
//		this.organizationId = organizationId;
//	}

	@JsonIgnore
	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public String getOrganizationBin() {
		return organizationBin;
	}

	public void setOrganizationBin(String organizationBin) {
		this.organizationBin = organizationBin;
	}
}
