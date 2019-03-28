package com.troy.rediscluster;

import com.troy.rediscluster.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    public IStudentService iStudentService;

    @RequestMapping("/get/{id}")
    public String getStu(@PathVariable("id") String id){
        System.out.println();
        String str = iStudentService.getStudent(id);
        return "get执行成功==="+str;
    }

    @RequestMapping("/add")
    public String addStu(@RequestParam(value="id") int id,
                         @RequestParam(value="name") String name,
                         @RequestParam(value="age") String age){
        Student stu = new Student(id,name,age);
        String str = iStudentService.addStudent(stu);
        return "add执行成功===" + str;
    }


    @RequestMapping("/update")
    public String updateStu(@RequestParam(value="id") int id,
                         @RequestParam(value="name") String name,
                         @RequestParam(value="age") String age){
        Student stu = new Student(id,name,age);
        System.out.println(stu.toString());
        String idStr = String.valueOf(id);
        String strOld = (String)iStudentService.getStudent(idStr);
        iStudentService.updateStudent(stu);
        String strNew = (String)iStudentService.getStudent(idStr);
        StringBuilder build = new StringBuilder();
        build.append(strOld).append("\t");
        build.append(strNew).append("\t");
        System.out.println(build.toString());
        return "update执行成功===" + build.toString();
    }
}
