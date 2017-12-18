package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.entity.StudentDO;
import com.tangcheng.app.domain.errorcode.GlobalCode;
import com.tangcheng.app.domain.errorcode.StudentError;
import com.tangcheng.app.domain.exception.BizException;
import com.tangcheng.app.domain.vo.ResultData;
import com.tangcheng.app.service.biz.StudentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Api(tags = "learning transaction", description = "learning 基于db和redis的事务")
@Transactional
@RestController
@RequestMapping(value = "tx/student")
public class StudentController {

    public static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping("save")
    public ResultData<?> save(@RequestParam(value = "hasError", defaultValue = "false") Boolean hasError) {
        int age = ThreadLocalRandom.current().nextInt(200);
        StudentDO studentDO = new StudentDO();
        studentDO.setName("name" + age);
        studentDO.setAge(age);
        studentDO.setClasses("classes" + age);
        studentService.saveStudent(studentDO, hasError);
        return ResultData.builder().build();
    }

    @GetMapping("list")
    public ResultData<List<StudentDO>> list(@RequestParam(value = "hasError", defaultValue = "false") Boolean hasError,
                                            @RequestParam(value = "pageId", defaultValue = "0") Integer pageId,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return studentService.listAll(hasError, pageId, pageSize);
    }

    @GetMapping(value = "{id}")
    public ResultData<StudentDO> getStudent(@PathVariable("id") Long id) {
        if (id < 1) {
            LOGGER.warn("invalid id:{}", id);
            throw new BizException(StudentError.NotExist);
        }
        StudentDO studentDO = studentService.getOne(id);
        return ResultData.<StudentDO>builder().detail(studentDO).build();
    }

}
