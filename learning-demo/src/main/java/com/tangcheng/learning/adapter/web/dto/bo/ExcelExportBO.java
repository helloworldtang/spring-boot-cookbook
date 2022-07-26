package com.tangcheng.learning.adapter.web.dto.bo;

import lombok.Data;
import org.apache.commons.text.RandomStringGenerator;
import org.joda.time.LocalDateTime;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 7/25/2018 8:34 AM
 */
@Data
public class ExcelExportBO<T> {
    private String excelFileName;
    private String sheetName;
    private List<String> columnNames;
    private List<T> content;

    public String getExcelFileName() {
        if (this.excelFileName == null) {
            String generateStrCode = new RandomStringGenerator.Builder()
                    .withinRange('a', 'z')
                    .build().generate(2);
            return generateStrCode + LocalDateTime.now().toString("yyyyMMddHHmmssSSS");
        }
        try {
            return URLEncoder.encode(excelFileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "tmp";
    }
}
