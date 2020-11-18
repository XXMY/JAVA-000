package com.cfw.geektime.java000;

import com.cfw.geektime.java000.pojo.Klass;
import com.cfw.geektime.java000.pojo.School;
import com.cfw.geektime.java000.pojo.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AutoConfiguration {
    @Bean
    @ConditionalOnClass(value = Student.class)
    @ConditionalOnMissingBean(value = Student.class)
    public Student student100(){
        return new Student();
    }

    @Bean
    @ConditionalOnMissingBean(value = Klass.class)
    public Klass klass(Student student){
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Klass klass = new Klass();
        klass.setStudents(studentList);

        return klass;
    }

    @Bean
    @ConditionalOnMissingBean(value = School.class)
    public School school(Student student, Klass klass){
        School school = new School();
        school.setStudent100(student);
        school.setClass1(klass);

        return school;
    }
}
