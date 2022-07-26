package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.learning.adapter.web.dto.req.CompanyReq;
import com.tangcheng.learning.util.CustomBeanUtils;
import com.tangcheng.learning.adapter.web.dto.req.group.CreateGroup;
import com.tangcheng.learning.adapter.web.dto.req.group.UpdateGroup;
import com.tangcheng.learning.adapter.web.entity.Company;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/07 10:44
 */
@Slf4j
@Api(tags = "SpringMVC中请求校验高级技巧", description = "SpringMVC中请求校验高级技巧")
@RestController
@RequestMapping("v1/spring_mvc/senior/validated")
public class RequestValidatedDemoController {

    private static Map<Integer, Company> ACTIVITY_DATA_CACHE = new ConcurrentHashMap<>();

    @ApiOperation(value = "创建一个公司", notes = "创建一个公司")
    @PostMapping("companies")
    public ResponseEntity<Integer> createActivity(@Validated({CreateGroup.class}) @RequestBody CompanyReq req) {
        Company company = CustomBeanUtils.copyProperties(req, Company.class);
        int id = ACTIVITY_DATA_CACHE.size() + 1;
        company.setId(id);
        ACTIVITY_DATA_CACHE.put(id, company);
        return ResponseEntity.ok(id);
    }


    @ApiOperation(value = "修改公司信息", notes = "修改公司信息")
    @PutMapping("companies/{companyId}")
    public ResponseEntity<Integer> createActivity(@PathVariable Integer companyId, @Validated({UpdateGroup.class}) @RequestBody CompanyReq req) {
        Company company = ACTIVITY_DATA_CACHE.get(companyId);
        if (company == null) {
            log.info("companyId{}不存在", companyId);
            throw new IllegalArgumentException("ID不存在");
        }
        CustomBeanUtils.copyProperties(req, company);
        ACTIVITY_DATA_CACHE.put(companyId, company);
        return ResponseEntity.ok(1);
    }


    @ApiOperation(value = "查看一个公司信息", notes = "查看一个公司信息")
    @GetMapping("companies/{companyId}")
    public ResponseEntity<Company> getOneActivity(@PathVariable Integer companyId) {
        Company company = ACTIVITY_DATA_CACHE.get(companyId);
        if (company == null) {
            log.info("companyId{}不存在", companyId);
            throw new IllegalArgumentException("ID不存在");
        }
        return ResponseEntity.ok(company);
    }


}
