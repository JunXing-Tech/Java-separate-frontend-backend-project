package com.yupi.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.UserLoginRequest;
import com.yupi.usercenter.model.domain.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.yupi.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.yupi.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * JunXing
 * 2023/5/7 22:00
 * IntelliJ IDEA
 */

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // todo 控制层封装请求

    @Resource
    private UserService userService;

    /** 用户注册 */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            //return null;
            //return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)){
            return null;
        }
        //return  result  = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        long result  = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        //return new BaseResponse<>(0, result, "ok");
        return ResultUtils.success(result);
    }

    /** 用户登录 */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        //return userService.userLogin(userAccount, userPassword, request);
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /** 退出登录 */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if(request == null){
            return null;
        }
        //return userService.userLogout(request);
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /** 获取用户当前状态 */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            return null;
        }
        long userId = currentUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(userId);
        //return userService.getSafetyUser(user);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /** 查询全部用户（仅管理员可用） */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request){
        //仅管理员可查询
        if(! isAdmin(request)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "普通用户无权限");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        //return userList.stream().map(user -> {return userService.getSafetyUser(user);}).collect(Collectors.toList());
        List<User> list = userList.stream().map(user -> {return userService.getSafetyUser(user);}).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /** 用户逻辑删除（仅管理员可用） */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        //仅管理员可删除
        if(! isAdmin(request)){
            return null;
        }
        if(id <= 0){
            return null;
        }
        //return userService.removeById(id);
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 判断用户是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if(user == null || user.getUserRole() != ADMIN_ROLE){
            return false;
        }
        return true;
    }
}
