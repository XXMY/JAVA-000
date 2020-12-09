package com.cfw.geektime.java000;

import com.cfw.geektime.java000.mapper.StudentMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Week08Homework02 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Week08Homework02.class);

        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);
        System.out.println(studentMapper.selectStudent(10,13));
    }
}
