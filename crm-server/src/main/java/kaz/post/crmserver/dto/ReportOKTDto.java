package kaz.post.crmserver.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportOKTDto {
    private String name;
    private List<Integer> monthCount = new ArrayList<>();
}
