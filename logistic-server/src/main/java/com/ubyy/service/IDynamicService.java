package com.ubyy.service;

import com.ubyy.pojo.Coordinate;
import com.ubyy.pojo.Position;

import java.io.IOException;
import java.util.List;

public interface IDynamicService {

    /**
     * 传参调用python脚本
     */
    Coordinate TransferRecognition(List<Position> positions) throws IOException;

    /**
     * 传参调用python脚本（车辆信息）
     * @param positions
     * @return
     */
    Coordinate TransferRecognitionVehicle(List<Position> positions) throws IOException;


//    /**
//     * 将String类型的数组转换为List类型的数组
//     * @return
//     */
//    Coordinate StringToList(String str);
}
