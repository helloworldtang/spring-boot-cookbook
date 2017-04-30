package com.tangcheng.app.service.biz;

import com.tangcheng.app.dao.biz.StudentBiz;
import com.tangcheng.app.domain.entity.StudentDO;
import com.tangcheng.app.domain.vo.ResultData;
import com.tangcheng.app.service.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Service
public class StudentServiceImpl implements StudentService {

    public static final String STUDENT_LIST = "student:list";
    @Autowired
    private StudentBiz studentBiz;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void saveStudent(StudentDO studentDO, Boolean hasError) {
        studentBiz.saveStudent(studentDO);
        if (redisTemplate.hasKey(STUDENT_LIST)) {
            redisTemplate.boundListOps(STUDENT_LIST).leftPush(studentDO);
        }
        if (hasError) {
            throw new IllegalArgumentException("error.roll back");
        }
    }

    @Override
    public ResultData<List<StudentDO>> listAll(Boolean hasError, Integer pageId, Integer pageSize) {
        BoundListOperations<String, Object> boundListOps = redisTemplate.boundListOps(STUDENT_LIST);
        List doList;
        int fromIndex = pageId * pageSize;
        int toIndex = fromIndex + pageSize;
        if (redisTemplate.hasKey(STUDENT_LIST)) {
            doList= boundListOps.range(fromIndex, toIndex-1);
        } else {
            List<StudentDO> all = studentBiz.listAll();
            for (StudentDO studentDO : all) {
                boundListOps.leftPush(studentDO);
            }
            Collections.reverse(all);
            doList= PageTool.create(all,pageSize).get(pageId);
        }
        if (hasError) {
            throw new IllegalArgumentException("error.roll back");
        }
        return new ResultData<>(doList);
    }

    @Override
    public StudentDO getOne(Long id) {
        return studentBiz.getOne(id);
    }

}
