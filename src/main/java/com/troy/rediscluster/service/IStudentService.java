package com.troy.rediscluster.service;

import com.troy.rediscluster.Student;

import java.util.List;

public interface IStudentService {
    public String getStudent(String key);
    public String addStudent(Student stu);
    public String updateStudent(Student stu);
    public String getStus(String key);
    public String addStus(List<Student> stus);
}
