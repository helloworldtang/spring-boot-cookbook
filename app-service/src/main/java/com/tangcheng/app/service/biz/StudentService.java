package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.entity.StudentDO;
import com.tangcheng.app.domain.vo.ResultData;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
public interface StudentService {
    void saveStudent(StudentDO studentDO, Boolean hasError);

    ResultData<List<StudentDO>> listAll(Boolean hasError, Integer pageId, Integer pageSize);

    StudentDO getOne(Long id);
}
