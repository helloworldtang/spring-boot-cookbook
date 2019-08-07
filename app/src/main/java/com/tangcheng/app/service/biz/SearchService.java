package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.vo.SearchVO;

import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-19  18:46
 */
public interface SearchService {
    List<SearchVO> search(Byte type);
}
