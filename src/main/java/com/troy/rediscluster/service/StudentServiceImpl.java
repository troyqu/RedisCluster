package com.troy.rediscluster.service;

import com.troy.rediscluster.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{

    private final static String STUDENT_REDIS_KEY_PREFIX = "stu::";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private ValueOperations<String,Object> valueOperations;
    private ListOperations<String,Object> listOperations;

    @PostConstruct
    public void setUpOperations(){
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
    }

    /**
     * 因为已经在redistemplate配置了jackson，所以直接用string来接受返回值
     * @param id
     * @return
     */
    @Cacheable(value="stu",key="#id")
    public String getStudent(String id) {
        System.out.println("进入getStudent()方法获取对象。。。");
        String str = (String)valueOperations.get(STUDENT_REDIS_KEY_PREFIX+id);
        /**
         * 如果在项目中这里添加访问dao的操作; dao.getStuById(id)
         */
        return str;
    }

    /**
     * 方法体里面不需要在进行任何添加数据进入redis的操作，因为CachePut标记的注解已经自动将数据添加到redis中，key=stu::#stu.id
     * @param stu
     * @return
     */
    @CachePut(value="stu",key="#stu.id")
    public String addStudent(Student stu) {
        System.out.println("进入addStudent()方法添加对象。。。");
        /**
         * 如果在项目中这里添加访问dao的操作; dao.saveStu(stu)
         */
        return stu.toString();
    }

    /**
     * 方法体里面不需要在进行任何添加数据进入redis的操作，因为CachePut标记的注解已经自动将数据添加到redis中，key=stu::#stu.id
     * @param stu
     * @return
     */
    @CachePut(value="stu",key="#stu.id" )
    public String updateStudent(Student stu) {
        System.out.println("进入updateStudent()方法更新对象。。。");
        return stu.toString();
    }

    @Override
    public String getStus(String key) {
        return null;
    }

    @Override
    public String addStus(List<Student> stus) {
        return null;
    }
}
