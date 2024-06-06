
package com.example.model;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class PayOrder implements Serializable {

    private Long id;
    private Long bizOrderNo;
    private Long payOrderNo;
    private Long bizUserId;
    private String payChannelCode;
    private Integer amount;
    private Integer payType;
    private Integer status;
    private String expandJson;
    private String resultCode;
    private String resultMsg;
    private Timestamp paySuccessTime;
    private Timestamp payOverTime;
    private String qrCodeUrl;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long creater;
    private Long updater;
    private Boolean isDelete;
}
