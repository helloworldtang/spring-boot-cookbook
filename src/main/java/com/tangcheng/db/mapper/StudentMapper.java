package com.tangcheng.db.mapper;

import com.tangcheng.db.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student")
    List<Student> selectAll();

    @Insert("insert into student ")
    int insert(Student student);
}
