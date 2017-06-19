package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.vo.GeoVO;
import com.tangcheng.app.domain.vo.MapVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-09  19:55
 */
@Service
public class LocationServiceImpl implements LocationService {

    //    http://echarts.baidu.com/demo.html#map-china-dataRange
    private static final String[] eChartsProvince = {
            "北京",
            "天津",
            "上海",
            "重庆",
            "河北",
            "河南",
            "云南",
            "辽宁",
            "黑龙江",
            "湖南",
            "安徽",
            "山东",
            "新疆",
            "江苏",
            "浙江",
            "江西",
            "湖北",
            "广西",
            "甘肃",
            "山西",
            "内蒙古",
            "陕西",
            "吉林",
            "福建",
            "贵州",
            "广东",
            "青海",
            "西藏",
            "四川",
            "宁夏",
            "海南",
            "台湾",
            "香港",
            "澳门"
    };


    @Override
    public MapVO getLocationData() {
        List<GeoVO> geoVOs = new ArrayList<>();
        Map<String, Long> tmpData = new HashMap<>();
        tmpData.put("上海", 100L);
        ArrayList<String> newArrayList = newArrayList(eChartsProvince);
        Long max = 0L;
        for (Map.Entry<String, Long> entry : tmpData.entrySet()) {
            GeoVO geoRes = new GeoVO();
            String key = entry.getKey();
            geoRes.setName(key);
            Long value = entry.getValue();
            geoRes.setValue(value);
            geoVOs.add(geoRes);
            newArrayList.remove(key);
            max = NumberUtils.max(max, value);
        }
        for (String name : newArrayList) {
            GeoVO geoRes = new GeoVO();
            geoRes.setName(name);
            geoRes.setValue(0L);
            geoVOs.add(geoRes);
        }
        MapVO mapVO = new MapVO();
        mapVO.setMax(max);
        mapVO.setDetail(geoVOs);
        return mapVO;
    }

    public static void main(String[] args) {
        LocationServiceImpl locationService=new LocationServiceImpl();
        System.out.println(locationService.getLocationData());
    }
}
