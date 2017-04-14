package com.tangcheng.db.biz;

import com.tangcheng.db.entity.StudentDo;
import com.tangcheng.db.mapper.StudentDoMapper;
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

    public List<StudentDo> selectAll() {
        return studentDoMapper.selectAll();
    }

    public int insert(StudentDo studentDo) {
        return studentDoMapper.insertUseGeneratedKeys(studentDo);
    }

    public List<StudentDo> selectAll(Integer pageId, Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageId, pageSize);
        return studentDoMapper.selectByExampleAndRowBounds(null, rowBounds);
    }
}
