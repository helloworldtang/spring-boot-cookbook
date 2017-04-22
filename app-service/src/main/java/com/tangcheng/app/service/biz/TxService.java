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
public class TxService implements ITxService {

    @Autowired
    private StudentBiz studentBiz;

    @Override
    public void addRecord(Boolean hasError) {
        int age = ThreadLocalRandom.current().nextInt(200);
        StudentDo studentDo = new StudentDo();
        studentDo.setName("name" + age);
        studentDo.setAge(age);
        studentDo.setClasses("classes" + age);
        studentBiz.insert(studentDo);
        if (hasError) {
            throw new IllegalArgumentException("error.roll back");
        }
    }

    @Override
    public ResultData<List<StudentDo>> selectAll() {
        List<StudentDo> doList = studentBiz.selectAll();
        return new ResultData<>(doList);
    }

    @Override
    public ResultData<List<StudentDo>> selectAll(Boolean hasError, Integer pageId, Integer pageSize) {

        List<StudentDo> doList = studentBiz.selectAll(pageId, pageSize);

        return new ResultData<>(doList);
    }
}
