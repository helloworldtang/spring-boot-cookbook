package com.tangcheng.app.dao.repository;

import com.tangcheng.app.domain.entity.StudentDO;
import com.tangcheng.app.dao.repository.mapper.StudentDOMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Repository
public class StudentRepository {
    @Autowired
    private StudentDOMapper studentDoMapper;

    public List<StudentDO> listAll() {
        return studentDoMapper.selectAll();
    }

    public int saveStudent(StudentDO studentDO) {
        return studentDoMapper.insertUseGeneratedKeys(studentDO);
    }

    public List<StudentDO> listAll(Integer pageId, Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageId, pageSize);
        return studentDoMapper.selectByExampleAndRowBounds(null, rowBounds);
    }

    public StudentDO getOne(Long id) {
        return studentDoMapper.selectByPrimaryKey(id);
    }

}
