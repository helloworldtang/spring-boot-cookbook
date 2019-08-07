package com.tangcheng.app.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-09  19:52
 */
public class MapVO implements Serializable {
    private List<GeoVO> detail;
    private Long max;

    public List<GeoVO> getDetail() {
        return detail;
    }

    public void setDetail(List<GeoVO> detail) {
        this.detail = detail;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }
}
