package com.yupi.usercenter.exception;

import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * JunXing
 * 2023/5/13 10:25
 * IntelliJ IDEA
 */

/**
 * @RestControllerAdvice是一个注解，用于定义全局异常处理程序和全局数据绑定。
 * 它可以与@RestController、@Controller和@ResponseBody一起使用，以捕获在请求期间抛出的异常并将它们转换为HTTP响应。
 * 在一个应用程序中，只需要声明一个@RestControllerAdvice类即可处理所有控制器的异常。
 * 可以通过编写带有@ExceptionHandler注解的方法来处理特定的异常，并将它们映射到HTTP响应。
 * 此外，@RestControllerAdvice还可以使用@InitBinder注解定义全局数据绑定规则。
 */

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("businessException" + e.getMessage());
        return ResultUtils.
                error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeException" + e);
        return ResultUtils.
                error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
