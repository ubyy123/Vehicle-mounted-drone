package com.ubyy.pojo;

import lombok.Data;

/**
 * 接收前端传入的参数（坐标）
 */
@Data
public class Position {

    private float lng;
    private float lat;
    private float weight;

}
