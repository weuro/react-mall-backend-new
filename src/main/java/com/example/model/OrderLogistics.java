
package com.example.model;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class OrderLogistics implements Serializable {

    private Long orderId;
    private String logisticsNumber;
    private String logisticsCompany;
    private String contact;
    private String mobile;
    private String province;
    private String city;
    private String town;
    private String street;
    private Timestamp createTime;
    private Timestamp updateTime;
}
