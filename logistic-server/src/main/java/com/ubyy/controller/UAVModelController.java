package com.ubyy.controller;

import com.ubyy.pojo.Coordinate;
import com.ubyy.pojo.Position;
import com.ubyy.pojo.RespBean;
import com.ubyy.service.IDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/perce")
@CrossOrigin
public class UAVModelController {

    @Autowired
    private IDynamicService dynamicService;

    @GetMapping("/vrp")
    public RespBean responseToFront(){
        return RespBean.success("请求成功！");
    }

    @PostMapping("/vrp")
    public RespBean dataToFront(@RequestBody List<Position> positions) throws IOException {
        Coordinate coordinate = dynamicService.TransferRecognition(positions);
        if(coordinate.getTruck_route_list().size() == 0){
            return RespBean.error("调用失败！");
        }
        return RespBean.success("调用成功！", coordinate);
    }

    @PostMapping("/vehicle")
    public RespBean dataToVehicle(@RequestBody List<Position> positions) throws IOException {
        Coordinate coordinate = dynamicService.TransferRecognitionVehicle(positions);
        if(coordinate.getTruck_route_list().size() == 0){
            return RespBean.error("调用失败！");
        }
        return RespBean.success("调用成功！", coordinate);
    }

//    @GetMapping("/test")
//    public RespBean parameters(String string){
//        Coordinate coordinate = dynamicService.StringToList(string);
//        return RespBean.success("测试", coordinate);
//    }
}
