package com.tangcheng.learning.adapter.web.view;

import com.tangcheng.learning.adapter.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.adapter.web.dto.vo.WeatherVO;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 7/25/2018 6:48 AM
 */
public abstract class Excel2003ViewAbstractXlsView extends AbstractXlsView {

    public static final String EXPORT_DATA = "EXCEL_FILE_NAME";

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        ExcelExportBO<WeatherVO> exportBO = (ExcelExportBO<WeatherVO>) map.get(EXPORT_DATA);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        String userAgent = httpServletRequest.getHeader("User-Agent");
        String excelFileName = exportBO.encodeExcelFileName() + ".xls";
        if (null != userAgent && userAgent.toLowerCase().contains("firefox")) {
            excelFileName = excelFileName.replaceAll("\\s", "");
            httpServletResponse.setHeader("Content-Disposition", String.format("attachment;filename*=utf-8'zh_cn'%s", excelFileName));
        } else {
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + excelFileName);
        }
        fillSheet(exportBO, workbook);
    }

    protected abstract void fillSheet(ExcelExportBO<WeatherVO> map, Workbook workbook);

    /**
     * 一个默认的表头
     *
     * @param wb
     * @return
     */
    protected CellStyle defaultHeaderStyle(Workbook wb) {
        Font headFont = wb.createFont();
        headFont.setBold(true);
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 11);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(headFont);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

}
