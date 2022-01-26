package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_CALL_COURIER")
@SequenceGenerator(name = "id_gen", sequenceName = "call_courier_seq", initialValue = 60000000, allocationSize = 1)
public class CallCourierEntity extends AbstractAuditingEntity {

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "call_time_from", nullable = false)
	private String callTimeFrom;

	@Column(name = "call_time_to", nullable = false)
	private String callTimeTo;

	@Column(name = "sender_fio", nullable = false)
	private String senderFio;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "sender_index")
	private String senderIndex;

	@Column(name = "sender_address", nullable = false)
	private String senderAddress;

	@Column(name = "comment")
	private String comment;

	@Column(name = "req_number")
	private String reqNumber;

	@Column(name = "email")
	private String email;

	public String getCallTimeFrom() {
		return callTimeFrom;
	}

	public void setCallTimeFrom(String callTimeFrom) {
		this.callTimeFrom = callTimeFrom;
	}

	public String getCallTimeTo() {
		return callTimeTo;
	}

	public void setCallTimeTo(String callTimeTo) {
		this.callTimeTo = callTimeTo;
	}

	public String getSenderFio() {
		return senderFio;
	}

	public void setSenderFio(String senderFio) {
		this.senderFio = senderFio;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSenderIndex() {
		return senderIndex;
	}

	public void setSenderIndex(String senderIndex) {
		this.senderIndex = senderIndex;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReqNumber() {
		return reqNumber;
	}

	public void setReqNumber(String reqNumber) {
		this.reqNumber = reqNumber;
	}
}
