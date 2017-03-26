package com.tangcheng.service;

import com.tangcheng.api.ITxService;
import com.tangcheng.db.entity.Student;
import com.tangcheng.global.domain.ResultData;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
public class TxService implements ITxService {
    @Override
    public void addRecord(Boolean hasError) {

    }

    @Override
    public ResultData<List<Student>> getAll() {
        return null;
    }
}
