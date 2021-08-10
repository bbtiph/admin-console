package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * An organization legal Address.
 */
@Entity
@Table(name = "T_ORGANIZATION_LEGALADDRESS")
@SequenceGenerator(name = "id_gen", sequenceName = "org_legal_address_seq", initialValue = 60000000, allocationSize = 1)
public class OrganizationLegalAddressEntity extends AbstractAddressEntity {

	@Column(name = "organization_id")
	private Long organizationId;

	@Column(name = "organization_bin", length = 50)
	private String organizationBin;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationBin() {
		return organizationBin;
	}

	public void setOrganizationBin(String organizationBin) {
		this.organizationBin = organizationBin;
	}
}
