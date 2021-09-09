package kaz.post.crmserver.service;

import kaz.post.crmserver.dto.ReportByTransactionDto;
import kaz.post.crmserver.dto.UserDTO;
import kaz.post.crmserver.entity.*;
import kaz.post.crmserver.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

/**
 * Service class for managing users.
 */
@Service
public class ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportTransactionRepository reportTransactionRepository;


    public Long idGenerate() {
        Date dNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
        String unique = format.format(dNow);
        String uniqueNum="";
        for (int i=0; i<unique.length(); i++) {
            uniqueNum+=unique.charAt(i);
        }
        return Long.parseLong(uniqueNum);
    }

    public ResponseEntity<?> saveReportInf(ReportByTransactionDto reportByTransactionDto) {
        ReportTransactionEntity transactionEntity = new ReportTransactionEntity();
        transactionEntity.setNameOfTransaction(reportByTransactionDto.getNameOfTransaction());
        transactionEntity.setLinkOfExcel(reportByTransactionDto.getLinkOfExcel());

        reportTransactionRepository.saveAndFlush(transactionEntity);
        return new ResponseEntity<>("{"
                + "\"report\":\"" + reportByTransactionDto.getNameOfTransaction() + "\", "
                + "\"status\":\"ok\"}", HttpStatus.CREATED);
    }

    public ResponseEntity<List<ReportByTransactionDto>> getReportByTransaction(Map<String, String> allRequestParams) throws ParseException {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        ReportTransactionEntity transactionEntity = new ReportTransactionEntity();

        int pageNumber = 0;
        int pageSize = 5;
        String startDate = null;
        String endDate = null;
        Boolean isFullInf = null;
        String sortBy = "id";
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        if (allRequestParams.containsKey("start")) {
            startDate = (allRequestParams.get("start"));
        }
        if (allRequestParams.containsKey("end")) {
            endDate = (allRequestParams.get("end"));
        }
        if (allRequestParams.containsKey("isFullInf")) {
            isFullInf = Boolean.parseBoolean(allRequestParams.get("isFullInf"));
        }
        if (allRequestParams.containsKey("sortDirection")) {
            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;
        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }

        transactionEntity.setId(idGenerate());
        transactionEntity.setFromDate(startDate);
        transactionEntity.setNameOfTransaction("Отчет по проведенным транзакциям");
        transactionEntity.setToDate(endDate);
        transactionEntity.setLinkOfExcel("post.kz/admin/console/" + transactionEntity.getId());
        transactionEntity.setTypeReport(2);
        reportTransactionRepository.saveAndFlush(transactionEntity);

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        List<ReportByTransactionDto> reportByTransactionDtos = new ArrayList<>();
        if (isFullInf != null) {
            List<ReportTransactionEntity> listReport = null;
            listReport = reportTransactionRepository.findTransactionsBetweenTwoDateWithOutPageable(startDate, endDate);
            for (ReportTransactionEntity reportTransaction : listReport) {
                ReportByTransactionDto reportByTransactionDto = new ReportByTransactionDto(reportTransaction.getId(),
                        reportTransaction.getNameOfTransaction(),
                        reportTransaction.getTransactionDate(), reportTransaction.getLinkOfExcel());
                reportByTransactionDtos.add(reportByTransactionDto);
            }
        }else {
            Page<ReportTransactionEntity> listReport = null;
            listReport = reportTransactionRepository.findTransactionsBetweenTwoDate(startDate, endDate, pageableRequest);
            for (ReportTransactionEntity reportTransaction : listReport) {
                ReportByTransactionDto reportByTransactionDto = new ReportByTransactionDto(reportTransaction.getId(),
                        reportTransaction.getNameOfTransaction(),
                        reportTransaction.getTransactionDate(), reportTransaction.getLinkOfExcel());
                reportByTransactionDtos.add(reportByTransactionDto);
            }
        }

        return new ResponseEntity<>(reportByTransactionDtos, HttpStatus.OK);
    }

    public Long countReportByTransaction(Map<String, String> allRequestParams) {
        String startDate = null;
        String endDate = null;
        if (allRequestParams.containsKey("start")) {
            startDate = (allRequestParams.get("start"));
        }
        if (allRequestParams.containsKey("end")) {
            endDate = (allRequestParams.get("end"));
        }
        return reportTransactionRepository.findCountTransactionsBetweenTwoDate(startDate, endDate);
    }

}
