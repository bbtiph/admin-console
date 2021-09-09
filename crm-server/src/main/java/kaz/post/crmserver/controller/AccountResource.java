package kaz.post.crmserver.controller;

import io.swagger.annotations.ApiParam;
import kaz.post.crmserver.dto.ChangePasswordDto;
import kaz.post.crmserver.dto.LoginsDto;
import kaz.post.crmserver.dto.ReportByTransactionDto;
import kaz.post.crmserver.dto.UserDTO;
import kaz.post.crmserver.entity.ReportTransactionEntity;
import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.exceptions.RegistrationException;
import kaz.post.crmserver.repositories.ReportTransactionRepository;
import kaz.post.crmserver.service.AccountService;
import kaz.post.crmserver.service.ReportService;
import kaz.post.crmserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountResource {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportTransactionRepository reportTransactionRepository;

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
            @Valid @RequestBody UserDTO userDTO,
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

    @RequestMapping(value = "/download-excel-report-by-link", method = RequestMethod.GET)
    public ResponseEntity<?> downloadExcelReportByLink(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) throws ParseException {
        Optional<ReportTransactionEntity> transactionEntity = reportTransactionRepository.findById(Long.parseLong(allRequestParams.get("id")));
        allRequestParams.put("start", transactionEntity.get().getFromDate());
        allRequestParams.put("end", transactionEntity.get().getToDate());
        allRequestParams.put("isFullInf", "true");
        switch (transactionEntity.get().getTypeReport()) {
            case 1:
                return userService.getReportByUser(allRequestParams);

            case 2:
                return reportService.getReportByTransaction(allRequestParams);
        }
        return null;
    }

}

