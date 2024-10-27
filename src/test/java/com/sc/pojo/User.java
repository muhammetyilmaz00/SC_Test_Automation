package com.sc.pojo;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String job;
    String createdAt;
    String updatedAt;
}
