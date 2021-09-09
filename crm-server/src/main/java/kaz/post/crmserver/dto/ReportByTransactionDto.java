package kaz.post.crmserver.dto;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class ReportByTransactionDto {
    private Long id;
    private String nameOfTransaction;
    private DateTime transactionDate;
    private String linkOfExcel;

    public ReportByTransactionDto(Long id, String nameOfTransaction, DateTime transactionDate, String linkOfExcel) {
        this.id = id;
        this.nameOfTransaction = nameOfTransaction;
        this.transactionDate = transactionDate;
        this.linkOfExcel = linkOfExcel;
    }

    public ReportByTransactionDto() {
    }
}
