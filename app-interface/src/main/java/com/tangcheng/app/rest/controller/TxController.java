package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.entity.StudentDo;
import com.tangcheng.app.domain.vo.ResultData;
import com.tangcheng.app.service.biz.ITxService;
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
@RequestMapping(value = "/tx")
public class TxController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TxController.class);

    @Autowired
    private ITxService txService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultData<List<StudentDo>> insert(@RequestParam(value = "hasError", defaultValue = "false") Boolean hasError) {
        txService.addRecord(hasError);
        return txService.selectAll();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultData<List<StudentDo>> testTx(@RequestParam(value = "hasError", defaultValue = "false") Boolean hasError,
                                              @RequestParam(value = "pageId", defaultValue = "0") Integer pageId,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return txService.selectAll(hasError,pageId,pageSize);
    }

}
