package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.errorcode.StudentError;
import com.tangcheng.app.domain.exception.BizException;
import com.tangcheng.app.domain.query.Student;
import com.tangcheng.app.domain.vo.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tangcheng on 3/25/2017.
 */
@RestController
public class StudentHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentHandlerController.class);

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResultData<?> getStudent(@PathVariable("id") Long id) {
        if (id < 1) {
            LOGGER.warn("invalid id:{}", id);
            throw new BizException(StudentError.NotExist);
        }

        Student student = new Student();
        student.setName("Name" + id);
        student.setAge(ThreadLocalRandom.current().nextInt(100));
        return new ResultData<Object>(student);
    }

}
