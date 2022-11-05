package com.tangcheng.learning.business.download.po.bo;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/11/4 9:56 PM
 * @Description:
 */
@Data
public class DownloadData {
    /**
     * 字符串类型的数据
     */
    @ColumnWidth(20)
    private String stringTypeData;
    /**
     * 日期类型的数据
     */
    @ColumnWidth(30)
    private Date dateTypeData;
    /**
     * double类型的数据
     */
    @ColumnWidth(20)
    private Double doubleTypeData;

}
