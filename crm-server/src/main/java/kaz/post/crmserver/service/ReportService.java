package kaz.post.crmserver.service;

import kaz.post.crmserver.dto.ReportByTransactionDto;
import kaz.post.crmserver.dto.ReportOKTDto;
import kaz.post.crmserver.dto.UserDTO;
import kaz.post.crmserver.entity.*;
import kaz.post.crmserver.exportData.ExportData;
import kaz.post.crmserver.exportData.ExportDataUsers;
import kaz.post.crmserver.repositories.mail.*;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
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
    private UserService userService;

    @Autowired
    private ReportTransactionRepository reportTransactionRepository;

    @Autowired
    private ParcelLogRepository parcelLogRepository;
    @Autowired
    private PaymentCardRepository paymentCardRepository;
    @Autowired
    private BatchPackageRepository batchPackageRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CallCourierRepository callCourierRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private UserRepository userRepository;



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

    public void reportToYear(int year, HttpServletResponse response) throws IOException {
        response.setContentType("application/excel");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ReportOKTDto> res = new ArrayList<>();
        res  = getListOKT(year);


        ExportData excelExporter = new ExportData(res);


        excelExporter.export(response);
    }

    public void reportToUsers(Map<String, String> allRequestParams, HttpServletResponse response) throws IOException, ParseException {
        response.setContentType("application/excel");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<UserDTO> res = new ArrayList<>();
        res  = userService.getUsers(allRequestParams);


        ExportDataUsers excelExporter = new ExportDataUsers(res);


        excelExporter.export(response);
    }

    public List<ReportOKTDto> getListOKT(int year) {
        List<ReportOKTDto> res = new ArrayList<>();
        ReportOKTDto col1 = new ReportOKTDto();
        col1.setName("Перенаправлений на почтомат");
        res.add(col1);
        ReportOKTDto col2 = new ReportOKTDto();
        col2.setName("Перенаправлений на дом");
        res.add(col2);
        ReportOKTDto col3 = new ReportOKTDto();
        col3.setName("Перенаправлений на супермаркет");
        ReportOKTDto col4 = new ReportOKTDto();
        col4.setName("Заявкана выпуск карт ");
        ReportOKTDto col5 = new ReportOKTDto();
        col5.setName("Бронирование ");
        ReportOKTDto col6 = new ReportOKTDto();
        col6.setName("Поиск по ШПИ номеру");
        ReportOKTDto col7 = new ReportOKTDto();
        col7.setName("Трекинг");
        ReportOKTDto col8 = new ReportOKTDto();
        col8.setName("Предзаполение EMS");
        ReportOKTDto col9 = new ReportOKTDto();
        col9.setName("Регистрация");
        ReportOKTDto col10 = new ReportOKTDto();
        col10.setName("Адресный ярлык Посылок  и Заказных писем");
        ReportOKTDto col11 = new ReportOKTDto();
        col11.setName("Вызов курьера ");
        ReportOKTDto col12 = new ReportOKTDto();
        col12.setName("Партионный трекинг");

        for (int i=1; i<13; i++) {
            col3.getMonthCount().add(
                    parcelLogRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01")
            );
        }
        res.add(col3);

        for (int i=1; i<13; i++) {
            col4.getMonthCount().add(
                    paymentCardRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01")
            );
        }
        res.add(col4);

        for (int i=1; i<13; i++) {
            col5.getMonthCount().add(
                    bookingRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01")
            );
        }
        res.add(col5);

        for (int i=1; i<13; i++) {
            col7.getMonthCount().add(
                    trackingRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01")
            );
        }
        res.add(col6);
        res.add(col7);
        res.add(col8);

        for (int i=1; i<13; i++) {
            col9.getMonthCount().add(
                    userRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01"
                    ) +
                            organizationRepository.getCountByMonth(
                                    String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                                    String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01"
                            )
            );
        }
        res.add(col9);

        for (int i=1; i<13; i++) {
            col10.getMonthCount().add(
                    batchPackageRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01")
            );
        }
        res.add(col10);

        for (int i=1; i<13; i++) {
            col11.getMonthCount().add(
                    callCourierRepository.getCountByMonth(
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i) + "01",
                            String.valueOf(year) + (i < 10 ? "0" : "") + String.valueOf(i == 12 ? "01" : i+1) + "01")
            );
        }
        res.add(col11);
        res.add(col12);
        return res;
    }

}
