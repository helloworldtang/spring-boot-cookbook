package com.tangcheng.app.dao.biz;

import com.tangcheng.app.domain.entity.StudentDo;
import com.tangcheng.app.domain.mapper.StudentDoMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Repository
public class StudentBiz {
    @Autowired
    private StudentDoMapper studentDoMapper;

    public List<StudentDo> listAllStudents() {
        return studentDoMapper.selectAll();
    }

    public int saveStudent(StudentDo studentDo) {
        return studentDoMapper.insertUseGeneratedKeys(studentDo);
    }

    public List<StudentDo> listAllStudents(Integer pageId, Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageId, pageSize);
        return studentDoMapper.selectByExampleAndRowBounds(null, rowBounds);
    }
}
