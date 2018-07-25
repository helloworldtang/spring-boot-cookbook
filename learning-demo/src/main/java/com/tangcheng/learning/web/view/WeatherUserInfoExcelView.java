package com.tangcheng.learning.web.view;

import com.tangcheng.learning.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.web.dto.vo.WeatherVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
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

    // TODO: 2018/7/25 不同业务不同VO的泛型处理
    @Override
    protected void fillSheet(ExcelExportBO<WeatherVO> exportBO, Workbook workbook) {
        String sheetName = Optional.ofNullable(exportBO.getSheetName()).orElse("export");
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 18 * 256);


        Row header = sheet.createRow(0);
        CellStyle style = defaultHeaderStyle(workbook);
        Cell cell0 = header.createCell(0);
        cell0.setCellStyle(style);
        cell0.setCellValue("城市名");

        Cell cell1 = header.createCell(1);
        cell1.setCellStyle(style);
        cell1.setCellValue("天气情况");

        Cell cell2 = header.createCell(2);
        cell2.setCellStyle(style);
        cell2.setCellValue("当前时间");

        @SuppressWarnings("unchecked")
        List<WeatherVO> contentList = (List<WeatherVO>) Optional.ofNullable(exportBO.getContent()).orElse(Collections.EMPTY_LIST);

        // 定义Cell格式
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd  hh:mm:ss"));
        int rowCount = 1;
        for (WeatherVO weatherVO : contentList) {
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(weatherVO.getCityName());
            userRow.createCell(1).setCellValue(weatherVO.getWeatherDetail());
            Cell cellDate = userRow.createCell(2);
            cellDate.setCellStyle(dateCellStyle);
            cellDate.setCellValue(new Date());
        }
    }


}
