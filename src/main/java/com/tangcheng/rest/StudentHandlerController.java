package com.tangcheng.rest;

import com.tangcheng.domain.Student;
import com.tangcheng.domain.StudentError;
import com.tangcheng.global.exception.BizException;
import com.tangcheng.global.exception.ResultData;
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

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResultData<?> getStudent(@PathVariable("id") Long id) {
        if (id == -1) {
            throw new BizException(StudentError.NotExist);
        }

        Student student = new Student();
        student.setName("Name" + id);
        student.setAge(ThreadLocalRandom.current().nextInt(100));
        return new ResultData<Object>(student);
    }

}
