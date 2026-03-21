package com.xingyou.sercice.impl;

import com.xingyou.mapper.StudentMapper;
import com.xingyou.pojo.Student;
import com.xingyou.sercice.StudentService;
import com.xingyou.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(String id, String password) {
        // 先从数据库查出用户
        Student student = studentMapper.findById(id);
        // 验证密码
        if (student != null && PasswordUtil.matches(password, student.getPassword())) {
            return student;
        }
        return null;
    }

    @Override
    public int register(String id, String password) {
        // 加密密码
        String encodedPassword = PasswordUtil.encode(password);
        return studentMapper.register(id, encodedPassword);
    }

    @Override
    public Student findById(String id) {
        return studentMapper.findById(id);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }
}
