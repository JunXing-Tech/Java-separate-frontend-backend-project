package com.yupi.usercenter.common;

/**
 * JunXing
 * 2023/5/12 9:22
 * IntelliJ IDEA
 */
public class ResultUtils {

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data){
        //System.out.println("data: " + data);
        return new BaseResponse<>(0, data, "ok");
    }

    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description){
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String description){
        return new BaseResponse(errorCode.getCode(), errorCode.getCode(), description);
    }

    public static BaseResponse error(int code, String message, String description){
        return new BaseResponse(code, null,message, description);
    }

}
