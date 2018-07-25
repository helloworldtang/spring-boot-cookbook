package com.tangcheng.learning.web.view;

import com.tangcheng.learning.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.web.dto.vo.WeatherVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 7/25/2018 7:29 AM
 */
@Component
@Slf4j
public class WeatherUserInfoExcelView extends Excel2003ViewAbstractXlsView {


    @Override
    protected void fillSheet(ExcelExportBO<WeatherVO> exportBO, Workbook workbook) {
        String sheetName = Optional.ofNullable(exportBO.getSheetName()).orElse("export");
        Sheet sheet = workbook.createSheet(sheetName);
        Row header = sheet.createRow(0);
        @SuppressWarnings("unchecked")
        List<String> titleList = (List<String>) Optional.ofNullable(exportBO.getColumnNames()).orElse(Collections.EMPTY_LIST);
        for (int i = 0; i < titleList.size(); i++) {
            header.createCell(i).setCellValue(titleList.get(i));
        }

        @SuppressWarnings("unchecked")
        List<WeatherVO> contentList = (List<WeatherVO>) Optional.ofNullable(exportBO.getContent()).orElse(Collections.EMPTY_LIST);

        int rowCount = 1;
        for (WeatherVO weatherVO : contentList) {
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(weatherVO.getCityName());
            userRow.createCell(1).setCellValue(weatherVO.getWeatherDetail());
        }
    }


}
