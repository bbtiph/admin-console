package kaz.post.crmserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_BOOKING_COUNT")
@SequenceGenerator(name = "id_gen", sequenceName = "booking_count_seq", initialValue = 60000000, allocationSize = 1)
public class BookingCountEntity extends AbstractAuditingEntity {



	@Column
	private Long branchId;

	@Column
	private String laneId;

	@Column
	private Integer hourlyLimit = 4;

	@OneToOne(mappedBy="bookingCountEntity",cascade = CascadeType.REMOVE)
	private BookingEntity bookingEntity;

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}


	public Integer getHourlyLimit() {
		return hourlyLimit;
	}

	public void setHourlyLimit(Integer hourlyLimit) {
		this.hourlyLimit = hourlyLimit;
	}
}
