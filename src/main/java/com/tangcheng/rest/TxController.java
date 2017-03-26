package com.tangcheng.rest;

import com.tangcheng.api.ITxService;
import com.tangcheng.db.entity.Student;
import com.tangcheng.global.domain.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
@RestController
public class TxController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TxController.class);

    @Autowired
    private ITxService txService;

    @RequestMapping(value = "/test2/exception", method = RequestMethod.POST)
    public ResultData<List<Student>> test2WriteWithException(@RequestParam(value = "hasError") Boolean hasError) {
        txService.addRecord(hasError);
        return txService.getAll();
    }

}
