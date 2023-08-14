package com.ubyy.pojo;


import com.ubyy.constant.HttpStatusEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RespMailBean {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 把构造方法私有化
    private RespMailBean() {}

    // 成功静态方法
    public static RespMailBean ok() {
        RespMailBean respMailBean = new RespMailBean();
        respMailBean.setSuccess(true);
        respMailBean.setCode(200);
        respMailBean.setMessage("成功");
        return respMailBean;
    }

    // 失败静态方法
    public static RespMailBean error(HttpStatusEnum httpStatus) {
        RespMailBean respMailBean = new RespMailBean();
        respMailBean.setSuccess(false);
        respMailBean.setCode(httpStatus.getCode());
        respMailBean.setMessage(httpStatus.getMsg());
        return respMailBean;
    }



    public RespMailBean data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
}
