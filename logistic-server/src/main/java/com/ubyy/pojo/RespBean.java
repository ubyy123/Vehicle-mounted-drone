package com.ubyy.pojo;

import com.ubyy.constant.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * 公共返回对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200, message, null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message, Object obj){
        return new RespBean(200, message, obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500, message, null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message, Object obj){
        return new RespBean(500, message, obj);
    }

//    /**
//     * HttpStatusEnum失败返回结果
//     * @param httpStatusEnum
//     * @return
//     */
//    public static RespBean error(HttpStatusEnum httpStatusEnum){
//        RespBean respBean = new RespBean();
//        respBean.setCode(httpStatusEnum.getCode());
//        respBean.setMessage(httpStatusEnum.getMsg());
//        return respBean;
//    }
}
