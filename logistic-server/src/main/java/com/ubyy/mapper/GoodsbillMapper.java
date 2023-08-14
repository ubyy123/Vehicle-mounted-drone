package com.ubyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ubyy.pojo.Goodsbill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangbiao
 * @since 2022-08-05
 */

public interface GoodsbillMapper extends BaseMapper<Goodsbill> {

    /**
     * 根据用户订单查询订单详细资料
     *
     * @param sendGoodCustomNo
     */
    Goodsbill getByNo(@Param("sendGoodCustomNo") String sendGoodCustomNo);

    /**
     * 根据收件人查询订单信息
     * @param acceptGoodCustom
     * @return
     */
    List<Goodsbill> getBillByCustom(@Param("acceptGoodCustom") String acceptGoodCustom);

    /**
     * 显示所有未交货订单
     * @return
     */
    List<Goodsbill> unfilledOrders(@Param("choose") String choose);

    /**
     * 根据收件人手机号查询订单信息
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
//    Integer getToBeSendCount();
}
