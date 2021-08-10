package kaz.post.crmserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_USER_SERVICE")
@SequenceGenerator(name = "id_gen", sequenceName = "user_service_seq", initialValue = 60000000, allocationSize = 1)
public class UserServiceEntity extends AbstractEntity {

	@Column(name = "user_id")
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private ServiceEntity serviceEntity;

	@Column(name = "organization_bin")
	private  String bin;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ServiceEntity getServiceEntity() {
		return serviceEntity;
	}

	public void setServiceEntity(ServiceEntity serviceEntity) {
		this.serviceEntity = serviceEntity;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

}
