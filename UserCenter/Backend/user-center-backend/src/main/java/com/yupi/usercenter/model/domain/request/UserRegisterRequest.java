package com.yupi.usercenter.model.domain.request;

/**
 * JunXing
 * 2023/5/8 9:14
 * IntelliJ IDEA
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 5679023018454962004L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String planetCode;
}
