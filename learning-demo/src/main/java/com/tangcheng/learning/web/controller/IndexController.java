package com.tangcheng.learning.web.controller;

import com.google.common.collect.ImmutableList;
import com.tangcheng.learning.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.web.dto.vo.WeatherVO;
import com.tangcheng.learning.web.view.WeatherUserInfoExcelView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.tangcheng.learning.web.view.Excel2003ViewAbstractXlsView.*;

/**
 * @author tangcheng
 * 2018/03/06
 */
@Api(hidden = true, tags = "WEB层", description = "WEB层")
@Controller
public class IndexController {

    @Autowired
    private WeatherUserInfoExcelView weatherUserInfoExcelView;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("download")
    public ModelAndView export() {
        ModelAndView modelAndView = new ModelAndView(weatherUserInfoExcelView);
        ExcelExportBO<WeatherVO> exportBO=new ExcelExportBO<>();
        exportBO.setExcelFileName("城市天气情况");
        exportBO.setSheetName("城市天气情况");
        exportBO.setColumnNames(ImmutableList.of("城市名", "天气情况"));
        List<WeatherVO> weatherVOList = new ArrayList<>();
        weatherVOList.add(WeatherVO.builder().cityName("北京").weatherDetail("雷阵雨转多云").build());
        weatherVOList.add(WeatherVO.builder().cityName("上海").weatherDetail("多云转晴").build());
        exportBO.setContent(weatherVOList);
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.put(EXPORT_DATA, exportBO);
        return modelAndView;
    }
}
