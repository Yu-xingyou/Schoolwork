package com.xingyou.sercice;

import com.xingyou.pojo.Student;

public interface StudentService {
    Student login(String id, String password);

    int register(String id, String password);

    Student findById(String id);

    int update(Student student);
}
