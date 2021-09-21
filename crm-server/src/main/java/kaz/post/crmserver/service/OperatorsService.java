package kaz.post.crmserver.service;

import kaz.post.crmserver.dto.OperatorsDto;
import kaz.post.crmserver.dto.ReportByTransactionDto;
import kaz.post.crmserver.dto.UserDTO;
import kaz.post.crmserver.entity.AuthorityEntity;
import kaz.post.crmserver.entity.ReportTransactionEntity;
import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.entity.payment.OfflineOperator;
import kaz.post.crmserver.entity.payment.Operators;
import kaz.post.crmserver.exceptions.MsgParam;
import kaz.post.crmserver.repositories.mail.ReportTransactionRepository;
import kaz.post.crmserver.repositories.payment.OfflineOperatorsRepository;
import kaz.post.crmserver.repositories.payment.OperatorsRepository;
import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@AllArgsConstructor
public class OperatorsService {

    private final Logger log = LoggerFactory.getLogger(OperatorsService.class);

    private final OperatorsRepository operatorsRepository;
    private final OfflineOperatorsRepository offlineOperatorsRepository;

    public ResponseEntity<List<OperatorsDto>> getAllOperators(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        int pageNumber = 0;
        int pageSize = 5;
        String input = "";
        String field = "";
        String sortBy = "id";
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        if (allRequestParams.containsKey("input")) {
            input = (allRequestParams.get("input"));
        }
        if (allRequestParams.containsKey("field")) {
            field = (allRequestParams.get("field"));
        }
        if (allRequestParams.containsKey("sortDirection")) {
            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;
        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        System.out.println(pageableRequest + ">>> ttt <<<" + input + " " + " <> " + field);
        Page<OfflineOperator> offlineOperators = null;
        Boolean type = null;
        switch (field) {
            case "NAME_OF_SERVICE":
                offlineOperators = offlineOperatorsRepository.findAllByContractorNameContains(input, pageableRequest);
                break;

            case "TYPE":
                if (input.equals("true")) {
                    type = true;
                } else if (input.equals("false")) {
                    type = false;
                }
                offlineOperators = offlineOperatorsRepository.findAllByOnline(type, pageableRequest);
                break;

            case "IS_ACTIVE":;
                if (input.equals("true")) {
                    type = true;
                } else if (input.equals("false")) {
                    type = false;
                }
                offlineOperators = offlineOperatorsRepository.findAllByEnabled(type, pageableRequest);
                break;

            default:
                offlineOperators = offlineOperatorsRepository.findAll(pageableRequest);
                break;
        }

        if (input.length() == 0 || input == null || input.isEmpty()) {
            offlineOperators = offlineOperatorsRepository.findAll(pageableRequest);
        }

        List<OperatorsDto> offlineOperatorList = new ArrayList<>();
        for (OfflineOperator offlineOperator : offlineOperators) {
            OperatorsDto operatorsDto = new OperatorsDto(offlineOperator.getId(),
                    offlineOperator.isEnabled(), offlineOperator.getContractorName(),
                    offlineOperator.getOnline(), null);
            offlineOperatorList.add(operatorsDto);
        }
        return new ResponseEntity<>(offlineOperatorList, HttpStatus.OK);
    }

    public long getAllOperatorsCount() {
        System.out.println(offlineOperatorsRepository.getAllOperatorsCount() + ">>rr");
        return offlineOperatorsRepository.getAllOperatorsCount();
    }

    public ResponseEntity<?> saveService(OperatorsDto operatorsDto){
        offlineOperatorsRepository.findById(operatorsDto.getId()).ifPresent(operator -> {
            operator.setEnabled(operatorsDto.isEnabledd());
            operator.setOnline(operatorsDto.getOnlinee());
            offlineOperatorsRepository.save(operator);
        });

        return new ResponseEntity<>("OK", HttpStatus.OK);

    }


}
