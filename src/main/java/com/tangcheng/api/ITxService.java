package com.tangcheng.api;

import com.tangcheng.db.entity.Student;
import com.tangcheng.global.domain.ResultData;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
public interface ITxService {
    void addRecord(Boolean hasError);

    ResultData<List<Student>> getAll();
}
