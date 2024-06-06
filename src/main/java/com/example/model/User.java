
package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private Timestamp create_time;
    private Timestamp update_time;
    private Integer status;
    private Integer balance;
}
