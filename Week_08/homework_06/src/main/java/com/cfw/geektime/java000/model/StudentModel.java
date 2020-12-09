package com.cfw.geektime.java000.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentModel {
    private int id;
    private String number;
    private String name;
    private int age;
    private int clazz;
    private String department;
}
