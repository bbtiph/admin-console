package kaz.post.crmserver.entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "T_REPORT_TRANSACTION")
@SequenceGenerator(name = "id_gen", sequenceName = "user_seq", initialValue = 60000000, allocationSize = 1)
public class ReportTransactionEntity extends AbstractEntity implements Serializable {

	@Column(name = "name_of_transaction")
	private String nameOfTransaction;

	@CreatedDate
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "transaction_date")
	private DateTime transactionDate = DateTime.now();;

	@Column(name = "link_of_excel", nullable = false)
	private String linkOfExcel;
	@Column(name = "from_date", nullable = false)
	private String fromDate;
	@Column(name = "to_date", nullable = false)
	private String toDate;
	@Column(name = "type_report", nullable = false)
	private int typeReport;
	@Column(name = "user_name", nullable = false)
	private String userName;
}


