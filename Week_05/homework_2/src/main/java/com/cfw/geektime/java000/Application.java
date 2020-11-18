package com.cfw.geektime.java000;

import com.cfw.geektime.java000.pojo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:applicationContext.xml")
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = (AnnotationConfigApplicationContext)SpringApplication.run(Application.class);
        System.out.println(applicationContext.getBean("personXml",Person.class));
        System.out.println(applicationContext.getBean("personBean",Person.class));
        System.out.println(applicationContext.getBean("personComponent",Person.class));

        Person person = new Person();
        person.setName("registerSingletonPerson");
        person.setAge(12);
        applicationContext.getBeanFactory().registerSingleton("personRegister",person);

        System.out.println(applicationContext.getBean("personRegister",Person.class));

    }

    @Bean
    public Person personBean(){
        Person person = new Person();
        person.setName("@Bean");
        person.setAge(11);

        return person;
    }
}
