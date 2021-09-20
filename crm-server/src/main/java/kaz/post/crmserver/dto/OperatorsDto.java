package kaz.post.crmserver.dto;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class OperatorsDto {
    private Long id;
    private boolean enabledd;
    private String contractorName;
    private Boolean onlinee;
    private DateTime datee;

    public OperatorsDto(Long id, boolean enabled, String contractorName, Boolean online, DateTime date) {
        this.id = id;
        this.enabledd = enabled;
        this.contractorName = contractorName;
        this.onlinee = online;
        this.datee = date;
    }
}
