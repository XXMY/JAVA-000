package com.cfw.geektime.java000;

import com.cfw.geektime.java000.model.StudentModel;
import com.cfw.geektime.java000.service.StudentService;
import com.cfw.geektime.java000.transaction.TransactionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TransactionConfiguration.class)
public class Week08Homework06 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Week08Homework06.class);

        StudentService studentService = applicationContext.getBean(StudentService.class);
        StudentModel studentModel = new StudentModel();
        studentModel.setId(125);
        studentModel.setAge(25);
        studentModel.setClazz(4);
        studentModel.setName("java000");
        studentModel.setNumber("01010101");
        studentModel.setDepartment("08");

        System.out.println(studentService.insert(studentModel));
    }
}
