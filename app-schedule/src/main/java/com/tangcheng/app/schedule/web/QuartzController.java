package com.tangcheng.app.schedule.web;

import com.tangcheng.app.schedule.domain.vo.JobVO;
import com.tangcheng.app.schedule.service.QuartzService;
import io.swagger.annotations.Api;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangcheng on 4/29/2017.
 */
@RestController
@RequestMapping("quartz")
@Api(tags = "Quartz接口", description = "测试接口详细描述")
public class QuartzController {

    public static final String SUCCESS = "success";

    private static final String NAME = "demo";
    private static final String GROUP = "demoGroup";

    @Autowired
    private QuartzService quartzService;

    @GetMapping("list")
    public ResponseEntity<Map<String, Object>> listAll() throws SchedulerException {
        Map<String, Object> result = new HashMap<>();
        List<JobVO> voList = quartzService.listAll();
        result.put("result", voList);
        result.put("size", voList.size());
        return ResponseEntity.ok(result);
    }

    @PostMapping("add")
    public ResponseEntity<String> save() throws SchedulerException {
        quartzService.save(NAME, GROUP);
        return ResponseEntity.ok(SUCCESS);
    }

    @PostMapping("remove")
    public ResponseEntity<String> remove() throws SchedulerException {
        quartzService.remove(NAME, GROUP);
        return ResponseEntity.ok(SUCCESS);
    }

    @PostMapping("pause")
    public ResponseEntity<String> pause() throws SchedulerException {
        quartzService.pause(NAME, GROUP);
        return ResponseEntity.ok(SUCCESS);
    }

    @PostMapping("resume")
    public ResponseEntity<String> resume() throws SchedulerException {
        quartzService.resume(NAME, GROUP);
        return ResponseEntity.ok(SUCCESS);
    }

}
