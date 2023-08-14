package com.ubyy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangbiao
 * @since 2022-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Goodsbill对象", description="")
public class Goodsbill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发货客户")
    private String sendGoodCustom;

    @ApiModelProperty(value = "发货客户电话")
    private String sendGoodCustomTel;

    @ApiModelProperty(value = "发货地址")
    private String sendAddress;

    @ApiModelProperty(value = "收货客户")
    private String acceptGoodCustom;

    @ApiModelProperty(value = "收货客户电话")
    private String acceptGoodCustomTel;

    @ApiModelProperty(value = "收货地址")
    private String acceptAddress;

    @ApiModelProperty(value = "发货日期")
    private String delivertDate;

    @ApiModelProperty(value = "货物重量")
    private float weight;

    @ApiModelProperty(value = "订单状态")
    private String shipStatus;

    @ApiModelProperty(value = "收货日期")
    private String acceptDate;

}
