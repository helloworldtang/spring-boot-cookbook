package com.tangcheng.app.service.biz;

import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by tangcheng on 5/26/2017.
 */
@Service
public class ETagServiceImpl implements ETagService {
    @Override
    public List<String> list() {
        return newArrayList("eTag1", "eTag2");
    }
}
