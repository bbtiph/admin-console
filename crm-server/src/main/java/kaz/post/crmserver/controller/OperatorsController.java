package kaz.post.crmserver.controller;

import io.swagger.annotations.ApiParam;
import kaz.post.crmserver.dto.OperatorsDto;
import kaz.post.crmserver.service.OperatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v2")
public class OperatorsController {

    @Autowired
    private OperatorsService operatorsService;

    @RequestMapping(value = "/get-all-operators", method = RequestMethod.GET)
    public ResponseEntity<List<OperatorsDto>> getAllOperators(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return operatorsService.getAllOperators(allRequestParams);
    }

    @RequestMapping(value = "/get-all-operators-count", method = RequestMethod.GET)
    public ResponseEntity<List<Long>> getAllOperatorsCount(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        long count = operatorsService.getAllOperatorsCount();
        List<Long> res = new ArrayList<>();
        res.add(count);
        System.out.println("r" + count);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
