package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.vo.SearchVO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-19  18:46
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public List<SearchVO> search(Byte type) {
        if (type == null) {
            return Collections.emptyList();
        }

        LocalDateTime now = LocalDateTime.now();
        List<SearchVO> voList = new ArrayList<>();
        SearchVO searchVO = new SearchVO();
        searchVO.setId(1L);
        searchVO.setType((byte) 1);
        searchVO.setUrl("http://pstatic.geekbang.org/pdf/595f443ecade9.pdf?e=1499773825&token=eHNJKRTldoRsUX0uCP9M3icEhpbyh3VF9Nrk5UPM:6i052AiZCMT-lyd5QmUTDBJj7mM=");
        LocalDateTime localDateTime = now.with(TemporalAdjusters.firstDayOfMonth());
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        searchVO.setTime(Date.from(instant).getTime());
        voList.add(searchVO);
        searchVO = new SearchVO();
        searchVO.setId(2L);
        searchVO.setType((byte) 1);
        searchVO.setUrl("http://pstatic.geekbang.org/pdf/595f322fa7bc0.pdf?e=1499773834&token=eHNJKRTldoRsUX0uCP9M3icEhpbyh3VF9Nrk5UPM:BW2fqbxf5_7f3ThhNYDOw4qtDR0=");
        searchVO.setTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()).getTime());
        voList.add(searchVO);
        return voList;
    }

}
