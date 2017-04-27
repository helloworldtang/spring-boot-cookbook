package com.tangcheng.app.service.biz;

import com.tangcheng.app.dao.biz.StudentBiz;
import com.tangcheng.app.domain.entity.StudentDo;
import com.tangcheng.app.domain.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Service
public class TxServiceImpl implements TxService {

    @Autowired
    private StudentBiz studentBiz;

    @Override
    public void saveStudent(Boolean hasError) {
        int age = ThreadLocalRandom.current().nextInt(200);
        StudentDo studentDo = new StudentDo();
        studentDo.setName("name" + age);
        studentDo.setAge(age);
        studentDo.setClasses("classes" + age);
        studentBiz.saveStudent(studentDo);
        if (hasError) {
            throw new IllegalArgumentException("error.roll back");
        }
    }

    @Override
    public ResultData<List<StudentDo>> listAllStudents() {
        List<StudentDo> doList = studentBiz.listAllStudents();
        return new ResultData<>(doList);
    }

    @Override
    public ResultData<List<StudentDo>> listAllStudents(Boolean hasError, Integer pageId, Integer pageSize) {

        List<StudentDo> doList = studentBiz.listAllStudents(pageId, pageSize);

        return new ResultData<>(doList);
    }

}
