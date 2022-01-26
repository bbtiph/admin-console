package kaz.post.crmserver.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by user on 13.07.2015.
 */
@Entity
@Table(name = "T_BOOKING")
@SequenceGenerator(name = "id_gen", sequenceName = "booking_seq", initialValue = 60000000, allocationSize = 1)
public class BookingEntity extends AbstractAuditingEntity {


	@Column
	private String token;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="booking_count_id", referencedColumnName="id")
	private BookingCountEntity bookingCountEntity;

	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;

	@Column
	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "lane_id")
	private String laneId;

	@Column(name = "branch_title")
	private String branchTitle;

	@Column(name = "lane_title")
	private String laneTitle;

	@Column(name="phone")
	private String phone;

	@Column(name="barcode")
	private String barcode;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

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

	public String getBranchTitle() {
		return branchTitle;
	}

	public void setBranchTitle(String branchTitle) {
		this.branchTitle = branchTitle;
	}

	public String getLaneTitle() {
		return laneTitle;
	}

	public void setLaneTitle(String laneTitle) {
		this.laneTitle = laneTitle;
	}

	public BookingCountEntity getBookingCountEntity() {
		return bookingCountEntity;
	}

	public void setBookingCountEntity(BookingCountEntity bookingCountEntity) {
		this.bookingCountEntity = bookingCountEntity;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
