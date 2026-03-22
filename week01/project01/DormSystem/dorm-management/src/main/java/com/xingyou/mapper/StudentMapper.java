package com.xingyou.mapper;

import com.xingyou.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentMapper {
    @Select("select student_id as id, password, dormitory_id as dormitoryId, create_time as createTime, update_time as updateTime from student where student_id=#{id} and password=#{password}")
    Student login(String id, String password);

    @Insert("insert into student(student_id,password) values(#{id},#{password})")
    int register(String id, String password);

    @Select("select student_id as id, password, dormitory_id as dormitoryId, create_time as createTime, update_time as updateTime from student where student_id=#{id}")
    Student findById(String id);

    @Update("update student set password=#{password}, dormitory_id=#{dormitoryId} where student_id=#{id}")
    int update(Student student);
}
