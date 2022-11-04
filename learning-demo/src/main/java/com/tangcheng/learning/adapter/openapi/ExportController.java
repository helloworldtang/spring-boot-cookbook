package com.tangcheng.learning.adapter.openapi;

import com.tangcheng.learning.adapter.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.adapter.web.dto.vo.WeatherVO;
import com.tangcheng.learning.adapter.web.view.Excel2003ViewAbstractXlsView;
import com.tangcheng.learning.adapter.web.view.WeatherUserInfoExcelView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/11/4 9:15 PM
 * @Description:
 */
@Api(tags = "导出")
@RestController
public class ExportController {

    @Autowired
    private WeatherUserInfoExcelView weatherUserInfoExcelView;

    @GetMapping("download/xls")
    public ModelAndView exportXls() {
        ModelAndView modelAndView = new ModelAndView(weatherUserInfoExcelView);
        ExcelExportBO<WeatherVO> exportBO = new ExcelExportBO<>();
        exportBO.setExcelFileName("城市天气情况");
        exportBO.setSheetName("城市天气情况");
        List<WeatherVO> weatherVOList = new ArrayList<>();
        weatherVOList.add(WeatherVO.builder().cityName("北京").weatherDetail("雷阵雨转多云").build());
        weatherVOList.add(WeatherVO.builder().cityName("上海").weatherDetail("多云转晴").build());
        exportBO.setContent(weatherVOList);
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.put(Excel2003ViewAbstractXlsView.EXPORT_DATA, exportBO);
        return modelAndView;
    }

}
