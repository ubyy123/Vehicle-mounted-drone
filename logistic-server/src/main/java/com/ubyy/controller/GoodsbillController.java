package com.ubyy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ubyy.pojo.Goodsbill;
import com.ubyy.pojo.RespBean;
import com.ubyy.service.IGoodsbillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangbiao
 * @since 2022-08-05
 */
@Api("订单controller")
@RestController
@RequestMapping("/goodsbill")
@CrossOrigin
public class GoodsbillController {

    @Autowired
    private IGoodsbillService goodsbillService;

    @ApiOperation("添加订单")
    @PostMapping("/addgoodsbill")
    public RespBean addGoodsbill(@RequestBody Goodsbill goodsbill){
        if(goodsbillService.save(goodsbill)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");

    }

    @ApiOperation("显示所有订单")
    @GetMapping("/getAllbills")
    public List<Goodsbill> listAllBills(){
        return goodsbillService.list();
    }

    @ApiOperation("查看订单状态")
    @GetMapping("/showOrdersState/{choose}")
    public List<Goodsbill> unfilledOrders(@PathVariable String choose){
        return goodsbillService.unfilledOrders(choose);
    }


    @ApiOperation("通过订单号查询订单信息")
    @GetMapping("/getDetailBillByNo/{sendGoodCustomNo}")
    public RespBean getDetailBillByNo(@PathVariable String sendGoodCustomNo){
        Goodsbill goodsbill = goodsbillService.getByNo(sendGoodCustomNo);
        if(goodsbill != null){
            return RespBean.success("查询成功！", goodsbill);
        }
        return RespBean.success("没有相应订单信息！");
    }

    @ApiOperation("通过收件人查询订单信息")
    @GetMapping("/getDetailBillByCustom/{acceptGoodCustom}")
    public RespBean getDetailBillByCustom(@PathVariable String acceptGoodCustom){
        List<Goodsbill> goodsbills = goodsbillService.getBillByCustom(acceptGoodCustom);
        if(goodsbills != null){
            return RespBean.success("查询成功！", goodsbills);
        }
        return RespBean.success("没有相应订单信息！");
    }

    @ApiOperation("通过手机号查询订单信息")
    @GetMapping("/getDetailBillByTel/{tel}")
    public RespBean getDetailBillByTel(@PathVariable String tel){
        List<Goodsbill> goodsbills = goodsbillService.getBillByTel(tel);
        if(goodsbills != null){
            return RespBean.success("查询成功！", goodsbills);
        }
        return RespBean.success("没有相应订单信息！");
    }

    @ApiOperation("查询当前日期的快递数")
    @GetMapping("/getGoodsCount")
    public RespBean getCurrentDate(){
        QueryWrapper<Goodsbill> wrapper = new QueryWrapper<>();
        QueryWrapper<Goodsbill> wrapper0 = new QueryWrapper<>();
        QueryWrapper<Goodsbill> wrapper1 = new QueryWrapper<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        Date now = new Date();
        Date now_5 = new Date(now.getTime() - 1000000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime_5 = dateFormat.format(now_5);
//        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        wrapper.apply("CREATE_TIME >= TO_DATE('" + format + "'00:00:00', 'yyyy-MM-dd HH24:MI:SS'");
//        wrapper.apply("CREATE_TIME <= TO_DATE('" + format + "'23:59:59', 'yyyy-MM-dd HH24:MI:SS'");
        wrapper.between("acceptDate", nowTime_5, now);
        int count = goodsbillService.count(wrapper);
        wrapper0.between("acceptDate", nowTime_5, now);
        wrapper0.like("shipStatus", "已送达");
        int count0 = goodsbillService.count(wrapper0);
        wrapper1.between("acceptDate", nowTime_5, now);
        wrapper1.like("shipStatus", "派送中");
        int count1 = goodsbillService.count(wrapper1);
        hashMap.put("已送达", count0);
        hashMap.put("派送中", count1);
        hashMap.put("快递总数", count);
        return RespBean.success("查询成功", hashMap);
    }

//    @ApiOperation("获取最近二十条数据信息")
//    public RespBean getTopTwenty(){
//        QueryWrapper<Goodsbill> wrapper = new QueryWrapper<>();
//        int count = goodsbillService.count();
//        wrapper.between("id", count-20, count);
//        Goodsbill one = goodsbillService.ge(wrapper);
//        return null;
//    }

//    @ApiModelProperty("查询当前时间前一分钟订单数")
//    public RespBean getCurrentDate(){
//        int count = goodsbillService.count();
//        HashMap<String, Integer> map = new HashMap<>();
//        Integer count1 = goodsbillService.getCountToBeSend();
//        if(count != 0){
//            return RespBean.success("查询成功", count);
//        }
//        return RespBean.error("查询错误");
//    }
}