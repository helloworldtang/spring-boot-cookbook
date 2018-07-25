package com.tangcheng.learning.web.view;

import com.tangcheng.learning.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.web.dto.vo.WeatherVO;
import org.apache.poi.ss.usermodel.Workbook;
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
        String excelName = exportBO.getExcelFileName() + ".xls";
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + excelName);
        fillSheet(exportBO, workbook);
    }

    protected abstract void fillSheet(ExcelExportBO<WeatherVO> map, Workbook workbook);

}
