package kaz.post.crmserver.controller;

import io.swagger.annotations.ApiParam;
import kaz.post.crmserver.dto.*;
import kaz.post.crmserver.entity.ReportTransactionEntity;
import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.exceptions.RegistrationException;
import kaz.post.crmserver.repositories.mail.ReportTransactionRepository;
import kaz.post.crmserver.service.AccountService;
import kaz.post.crmserver.service.ReportService;
import kaz.post.crmserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportTransactionRepository reportTransactionRepository;
    @Autowired
    private ReportTransactionRepository transactionRepository;

    private final String USER_TYPE = "USER";

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return userService.getAllUser(allRequestParams);
    }

    @RequestMapping(value = "/searchingWithFilter", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> searchingWithFilter(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return userService.searchingWithFilter(allRequestParams);
    }

    @RequestMapping(value = "/get-report-by-users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getReportByUser(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) throws ParseException {
        return userService.getReportByUser(allRequestParams);
    }

    @RequestMapping(value = "/get-count-report-by-users", method = RequestMethod.GET)
    public Long getCountReportByUser(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams){
        return userService.countReportByUser(allRequestParams);
    }

    @RequestMapping(value = "/getAllUsersCount", method = RequestMethod.GET)
    public Long getAllUsersCount(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return userService.countUser();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(
            @RequestBody UserDTO userDTO,
            @CookieValue(value = "_ga", required = false) String gaCookie,
            @RequestParam(value = "device", defaultValue = "web") String device) {
        return accountService.tryUserRegistry(userDTO, gaCookie, device, USER_TYPE);
    }

    @RequestMapping(value = "/refactoringUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refactoringUser(@RequestBody UserDTO userDTO) {
        System.out.println("66> " + userDTO.getLogin());
        return accountService.refactoring(userDTO);
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity getUserById(@RequestParam(value = "login") String login) {
        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/deleteUsersByLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUsersByLogin(@RequestBody LoginsDto listLogins) {
        return accountService.deleteUsersByLogin(listLogins.getLogins());
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return accountService.changePassword(changePasswordDto.getPassword(), changePasswordDto.getUserDTO());
    }

    @RequestMapping(value = "/getUserFioByIin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserFioByIin(@RequestParam(value = "iin") String iin) throws RegistrationException {
        return accountService.getUserFioByiIn(iin);
    }

    @RequestMapping(value = "/save_report", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReport(
            @Valid @RequestBody ReportByTransactionDto reportByTransactionDto) {
        return reportService.saveReportInf(reportByTransactionDto);
    }

    @RequestMapping(value = "/get-report-by-transaction", method = RequestMethod.GET)
    public ResponseEntity<List<ReportByTransactionDto>> getReportByTransaction(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) throws ParseException {
        return reportService.getReportByTransaction(allRequestParams);
    }

    @RequestMapping(value = "/get-count-report-by-transaction", method = RequestMethod.GET)
    public Long getCountReportByTransaction(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams){
        return reportService.countReportByTransaction(allRequestParams);
    }

    @RequestMapping(value = "/download-excel-report-by-link/{id}", method = RequestMethod.GET)
    public void downloadExcelReportByLink(@PathVariable int id, HttpServletResponse response) throws IOException, ParseException {
        Map<String, String> allRequestParams = new HashMap<>();
        Optional<ReportTransactionEntity> transactionEntity = reportTransactionRepository.findById(Long.valueOf(id));
        allRequestParams.put("start", transactionEntity.get().getFromDate());
        allRequestParams.put("end", transactionEntity.get().getToDate());
        allRequestParams.put("isFullInf", "true");
        switch (transactionEntity.get().getTypeReport()) {
            case 1:
                reportService.reportToUsers(allRequestParams, response);
                break;

            case 3:
                exportToExcelOKT(Integer.valueOf(transactionEntity.get().getFromDate().substring(0,4)), response);
                break;
        }
    }



    @GetMapping("/export-by-okt/excel")
    public void exportToExcelOKT(int year, HttpServletResponse response) throws IOException {
        reportService.reportToYear(year, response);
    }

    @GetMapping("/export-by-users/excel")
    public void exportToExcelUsers(int year, HttpServletResponse response) throws IOException {
        reportService.reportToYear(year, response);
    }

    @GetMapping("/start-report-history")
    public void saveHistory(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) throws IOException {
        ReportTransactionEntity transactionEntity = new ReportTransactionEntity();

        String startDate = null;
        String endDate = null;
        String typeReport = null;
        String nameOfTransaction = null;
        if (allRequestParams.containsKey("start")) startDate = (allRequestParams.get("start"));
        if (allRequestParams.containsKey("end")) endDate = (allRequestParams.get("end"));
        if (allRequestParams.containsKey("type")) typeReport = (allRequestParams.get("type"));
        switch (Integer.valueOf(typeReport)) {
            case 1:
                nameOfTransaction = "Отчет по пользователям";
                break;

            case 3:
                nameOfTransaction = "Выгрузка статических данных";
                break;
        }
        Date dNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
        String unique = format.format(dNow);
        String uniqueNum="";
        for (int i=0; i<unique.length(); i++)
            uniqueNum+=unique.charAt(i);
        transactionEntity.setId(Long.parseLong(uniqueNum));
        transactionEntity.setFromDate(startDate);
        transactionEntity.setNameOfTransaction(nameOfTransaction);
        transactionEntity.setToDate(endDate);
        transactionEntity.setLinkOfExcel("post.kz/admin/console/" + transactionEntity.getId());
        transactionEntity.setTypeReport(Integer.valueOf(typeReport));
        transactionRepository.saveAndFlush(transactionEntity);
    }

    @GetMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return userService.resetPassword(allRequestParams.get("login"), allRequestParams.get("type"));
    }

    @GetMapping("/get-report-by-okt/{year}")
    public List<ReportOKTDto> getReportByOKT(@PathVariable int year) throws IOException {
        return reportService.getListOKT(year);
    }

}

