package com.cfw.geektime.java000.mapper;

import com.cfw.geektime.java000.model.StudentModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    @Select("SELECT id, number,name,age, class as clazz ,department FROM student WHERE id = #{id} AND age = #{age} LIMIT 1")
    StudentModel selectStudent(@Param("id")int id, @Param("age")int age);

    @Insert("INSERT INTO student VALUE (#{id},#{number},#{name},#{age},#{clazz},#{department})")
    int insertStudent(@Param("id")int id,@Param("number")String number, @Param("name")String name, @Param("age")int age, @Param("clazz")int clazz, @Param("department")String department);
}
