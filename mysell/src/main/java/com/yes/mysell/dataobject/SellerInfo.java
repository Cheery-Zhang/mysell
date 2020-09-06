package com.yes.mysell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openid; //微信openid
//    private Date create_time;//创建时间
//    private Date update_time;//更新时间

}
