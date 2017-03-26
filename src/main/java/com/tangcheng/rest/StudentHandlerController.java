package com.tangcheng.rest;

import com.tangcheng.db.entity.Student;
import com.tangcheng.db.entity.StudentError;
import com.tangcheng.global.domain.ResultData;
import com.tangcheng.global.exception.BizException;
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
