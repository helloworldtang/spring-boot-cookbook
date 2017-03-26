package com.tangcheng.service;

import com.tangcheng.api.ITxService;
import com.tangcheng.db.biz.StudentBiz;
import com.tangcheng.db.entity.StudentDo;
import com.tangcheng.global.domain.ResultData;
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
        if (hasError) {
            throw new IllegalArgumentException("error.roll back");
        }
        int age = ThreadLocalRandom.current().nextInt(200);
        StudentDo studentDo = new StudentDo();
        studentDo.setName("name" + age);
        studentDo.setAge((byte) age);
        studentDo.setClasses("classes" + age);
        studentBiz.insert(studentDo);
    }

    @Override
    public ResultData<List<StudentDo>> getAll() {
        List<StudentDo> doList = studentBiz.selectAll();
        return new ResultData<>(doList);
    }
}
