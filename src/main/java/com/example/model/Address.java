package com.example.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Address implements Serializable {

    private Long id;
    private Long userId;
    private String province;
    private String city;
    private String town;
    private String mobile;
    private String street;
    private String contact;
    private String isDefault;
    private String notes;
}
