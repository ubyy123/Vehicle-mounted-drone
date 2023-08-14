package com.ubyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ubyy.mapper.GoodsbillMapper;
import com.ubyy.pojo.Goodsbill;
import com.ubyy.service.IGoodsbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangbiao
 * @since 2022-08-05
 */
@Service
public class GoodsbillServiceImpl extends ServiceImpl<GoodsbillMapper, Goodsbill> implements IGoodsbillService {

    @Autowired
    private GoodsbillMapper goodsbillMapper;

    /**
     * 根据订单号查询订单信息
     * @param sendGoodCustomNo
     * @return
     */
    @Override
    public Goodsbill getByNo(String sendGoodCustomNo) {
        Goodsbill goodsbill = goodsbillMapper.getByNo(sendGoodCustomNo);
        return goodsbill;
    }

    /**
     * 根据收件人查询订单信息
     * @param acceptGoodCustom
     * @return
     */
    @Override
    public List<Goodsbill> getBillByCustom(String acceptGoodCustom) {
        List<Goodsbill> goodsbills = goodsbillMapper.getBillByCustom(acceptGoodCustom);
        return goodsbills;
    }

    /**
     * 显示所有未交货订单
     * @return
     * @param choose
     */
    @Override
    public List<Goodsbill> unfilledOrders(String choose) {
        return goodsbillMapper.unfilledOrders(choose);
    }


    /**
     * 根据手机号查询订单信息
     * @param tel
     * @return
     */
    @Override
    public List<Goodsbill> getBillByTel(String tel) {
        return goodsbillMapper.getBillByTel(tel);
    }

//    /**
//     * 根据日期查询快递的数量
//     * @param date
//     * @return
//     */
//    @Override
//    public Integer getGoodsCount(String date) {
//
//        return goodsbillMapper.getGoodsCount(date);
//    }

//    /**
//     * 获取当前时间前一分钟待送货物的数量
//     * @return
//     */
//    @Override
//    public Integer getCountToBeSend() {
//        return goodsbillMapper.getToBeSendCount();
//    }
}
