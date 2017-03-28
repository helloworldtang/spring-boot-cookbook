package com.tangcheng.db.mapper;

import com.tangcheng.db.entity.StudentDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDoMapper {
    @Select("select * from student order by id")
    List<StudentDo> selectAll();

    @Insert("insert into student (name,age,classes)values(#{name},#{age},#{classes}) ")
    @Options(keyColumn = "id", useGeneratedKeys = true)
    int insertUseGeneratedKeys(StudentDo studentDo);
}