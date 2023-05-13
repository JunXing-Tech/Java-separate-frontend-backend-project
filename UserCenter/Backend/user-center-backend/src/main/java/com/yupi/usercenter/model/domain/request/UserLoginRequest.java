package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * JunXing
 * 2023/5/8 10:40
 * IntelliJ IDEA
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 5679023018454962004L;

    private String userAccount;

    private String userPassword;
}
