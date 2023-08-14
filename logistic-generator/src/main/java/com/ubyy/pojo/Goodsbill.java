package com.ubyy.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ApiModelProperty(value = "订单编号")
    private String sendGoodCustomNo;

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

    @ApiModelProperty(value = "预期交货日期")
    private String preDelivertDate;

    @ApiModelProperty(value = "实际交货日期")
    private String actualDelivertData;


}
