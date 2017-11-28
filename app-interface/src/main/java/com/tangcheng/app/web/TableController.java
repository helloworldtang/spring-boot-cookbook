package com.tangcheng.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangcheng
 * 2017/11/28
 */
@Controller
public class TableController {

    @GetMapping("/table/rowspan")
    public String tableRoleRowSpan(Model model) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, String> detail = new HashMap<>();
        detail.put("line1", "line1Value");
        detail.put("line2", "line2Value");
        detail.put("line3", "line3Value");
        Map<String, Object> record = new HashMap<>();
        record.put("record", detail);
        record.put("id", "Rowspan");
        list.add(record);
        model.addAttribute("list", list);
        return "table";
    }


}
