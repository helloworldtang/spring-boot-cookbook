package com.tangcheng.learning.web.api;

import com.tangcheng.learning.web.dto.req.CommodityCreateReq;
import com.tangcheng.learning.web.entity.Commodity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/04 17:01
 */
@Slf4j
@Api(tags = "RestAPI CRUD的一个示例", description = "RestAPI CRUD的一个示例")
@RequestMapping("/v1/rest/api/commodities")
@RestController
public class OrderRestAPIController {

    private Map<Integer, Commodity> allCommodities = new ConcurrentHashMap<>();

    @ApiOperation(value = "创建一个商品", notes = "创建一个商品")
    @PostMapping
    public ResponseEntity<Integer> createOrder(@Valid @RequestBody CommodityCreateReq req) {
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(req, commodity);
        commodity.setId(allCommodities.size() + 1);
        allCommodities.put(commodity.getId(), commodity);
        return ResponseEntity.ok(commodity.getId());
    }


    @ApiOperation(value = "修改一个信息", notes = "修改一个信息")
    @PutMapping("{commodityId}")
    public ResponseEntity<Integer> createOrder(@PathVariable Integer commodityId, @Valid @RequestBody CommodityCreateReq req) {
        Commodity commodity = allCommodities.get(commodityId);
        if (commodity == null) {
            throw new IllegalArgumentException("ID不存在:" + commodityId);
        }
        BeanUtils.copyProperties(req, commodity);
        allCommodities.put(commodityId, commodity);
        return ResponseEntity.ok(1);
    }


    @ApiOperation(value = "删除一条记录", notes = "删除一条记录")
    @DeleteMapping("{commodityId}")
    public ResponseEntity<Integer> deleteCommodity(@PathVariable Integer commodityId) {
        Commodity remove = allCommodities.remove(commodityId);
        if (remove != null) {
            return ResponseEntity.ok(1);
        }
        throw new IllegalArgumentException("ID不存在:" + commodityId);
    }

    @ApiOperation(value = "商品列表", notes = "商品列表")
    @GetMapping
    public ResponseEntity<List<Commodity>> listCommodity() {
        List<Commodity> result = new ArrayList<>(allCommodities.values());
        result.sort(Comparator.comparingInt(Commodity::getId));
        return ResponseEntity.ok(result);
    }


}
