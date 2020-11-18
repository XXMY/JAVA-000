package com.cfw.geektime.java000.pojo;

import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@ToString
@Component("personComponent")
public class Person {
    private String name;
    private int age;
}
