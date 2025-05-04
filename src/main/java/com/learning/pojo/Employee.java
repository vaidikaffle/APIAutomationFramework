package com.learning.pojo;


import lombok.Builder;
import lombok.Getter;

@Builder (setterPrefix = "set")
@Getter
public class Employee {


    private int id;
    private String firstname;
    private String lastname;
}
