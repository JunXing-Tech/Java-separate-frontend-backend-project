package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JunXing
 * 2023/5/6 10:26
 * IntelliJ IDEA
 */

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser(){
        User user = new User();
        user.setUsername("Test");
        user.setUserAccount("123");
        user.setAvatarUrl("https://ts4.cn.mm.bing.net/th?id=OIP-C.rHuc8SKa0wLVwCqqA27uIwHaEt&w=313&h=199&c=8&rs=1&qlt=90&o=6&dpr=1.7&pid=3.1&rm=2");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");

        boolean result = userService.save(user);
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        //密码不能为空
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        String planetCode = "123";
        long result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        //用户名 >= 4
        userAccount = "yu";
        result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        //密码不小于8位
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        //用户名不能有空白字符
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        //两次密码不相同
        checkPassword = "123456789";
        result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        //用户名不能重复
        userAccount = "dogYupi";
        userPassword = "123456789";
        result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);
        //成功
        userAccount = "yupi";
        result = userService.
                userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertTrue(result > 0);
    }
}