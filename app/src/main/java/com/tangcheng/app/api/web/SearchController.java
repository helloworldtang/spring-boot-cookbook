package com.tangcheng.app.api.web;

import com.tangcheng.app.domain.vo.SearchVO;
import com.tangcheng.app.service.biz.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-19  18:25
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public String search(@RequestParam Byte type, Model model) {
        List<SearchVO> voList = searchService.search(type);
        model.addAttribute("result", voList);
        return "search";
    }

    @GetMapping
    public String search() {
        return "search";
    }

}
