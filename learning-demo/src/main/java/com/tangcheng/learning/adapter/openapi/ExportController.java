package com.tangcheng.learning.adapter.openapi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.tangcheng.cookbook.common.domain.dto.ResultDTO;
import com.tangcheng.learning.adapter.web.dto.bo.ExcelExportBO;
import com.tangcheng.learning.adapter.web.dto.vo.WeatherVO;
import com.tangcheng.learning.adapter.web.view.Excel2003ViewAbstractXlsView;
import com.tangcheng.learning.adapter.web.view.WeatherUserInfoExcelView;
import com.tangcheng.learning.business.download.po.bo.DownloadData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/11/4 9:15 PM
 * @Description:
 */
@Slf4j
@Api(tags = "导出")
@RestController
public class ExportController {

    @Autowired
    private WeatherUserInfoExcelView weatherUserInfoExcelView;

    @ApiOperation("基于SpringWebMvc的AbstractView组件")
    @GetMapping("export/spring_web_mvc")
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

    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DownloadData}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     * <p>
     * 通过 HttpServletResponse 得到的 PrintWriter 和 ServletOutputStream 不需要手动关闭。
     * 通常您不应该关闭流。在 servlet 完成生命周期之后，servlet 容器会自动关闭流。
     * 举个例子，如果你关闭了流的话，在你实现的 Filter 中就不能再使用了。
     *
     * @since 2.1.1
     */
    @ApiOperation("基于EasyExcel组件")
    @GetMapping("export/easy_excel")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("自定义的文件名", "UTF-8").replaceAll("\\+", "%20");
            /**
             * Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
             * 服务器向浏览器发送文件时，如果是浏览器支持的文件类型，一般会默认使用浏览器打开，比如txt、jpg等。
             * 如果需要提示用户保存，就要利用Content-Disposition进行处理，（敲黑板，划重点）关键在于一定要加上attachment
             * filename就是当用户想把请求所得的内容存为一个文件的时候提供一个默认的文件名。
             */
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DownloadData.class)
                    .autoCloseStream(false)
                    .sheet("第一个sheet")
                    .doWrite(data());
        } catch (Exception e) {
            log.warn("报错了 {} ", e.getMessage(), e);
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().println(JSON.toJSONString(ResultDTO.fail("下载文件失败:" + e.getMessage())));
        }
    }

    private Collection<?> data() {
        List<DownloadData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DownloadData data = new DownloadData();
            data.setStringTypeData("字符串" + 0);
            data.setDateTypeData(new Date());
            data.setDoubleTypeData(0.56);
            list.add(data);
        }
        return list;
    }


}
