package com.ubyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ubyy.pojo.Goodsbill;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangbiao
 * @since 2022-08-05
 */
public interface IGoodsbillService extends IService<Goodsbill> {

    /**
     * 根据订单号查询订单信息
     * @param sendGoodCustomNo
     * @return
     */
    Goodsbill getByNo(String sendGoodCustomNo);

    /**
     * 根据收件人查询订单信息
     * @param acceptGoodCustom
     * @return
     */
    List<Goodsbill> getBillByCustom(String acceptGoodCustom);


    /**
     * 显示所有未交货订单
     * @return
     * @param choose
     */
    List<Goodsbill> unfilledOrders(String choose);

    /**
     * 根据手机号查询订单信息
     * @param tel
     * @return
     */
    List<Goodsbill> getBillByTel(String tel);


//    /**
//     * 根据日期查询快递的数量
//     * @param date
//     * @return
//     */
//    Integer getGoodsCount(String date);

//    /**
//     * 获取当前时间前一分钟待送货物的数量
//     * @return
//     */
//    Integer getCountToBeSend();
}
