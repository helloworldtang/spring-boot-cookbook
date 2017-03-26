package com.tangcheng.db.dao;

import com.tangcheng.db.entity.Student;
import com.tangcheng.db.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Repository
public class StudentBiz {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    public int insert(Student student) {
       return studentMapper.insert(student);
    }

}
