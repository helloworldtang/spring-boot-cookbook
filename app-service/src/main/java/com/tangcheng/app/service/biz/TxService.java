package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.entity.StudentDo;
import com.tangcheng.app.domain.vo.ResultData;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
public interface TxService {
    void addRecord(Boolean hasError);

    ResultData<List<StudentDo>> selectAll();

    ResultData<List<StudentDo>> selectAll(Boolean hasError, Integer pageId, Integer pageSize);
}
