package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.entity.StudentDo;
import com.tangcheng.app.domain.vo.ResultData;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
public interface TxService {
    void saveStudent(Boolean hasError);

    ResultData<List<StudentDo>> listAllStudents();

    ResultData<List<StudentDo>> listAllStudents(Boolean hasError, Integer pageId, Integer pageSize);
}
