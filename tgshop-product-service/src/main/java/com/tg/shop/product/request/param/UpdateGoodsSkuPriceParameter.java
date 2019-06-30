package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@ApiModel
public class UpdateGoodsSkuPriceParameter {

    @NotEmpty
    private String[] skuIds;

    /**
     * 成本价格
     */
    private BigDecimal costPrice;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 开启plus价格 0 否 1是
     */
    private Integer plusPriceOpen;

    /**
     * plus价格
     */
    private BigDecimal plusPrice;

    /**
     * 开启超级会员价 0 否 1是
     */
    private Integer superVipPriceOpen;

    /**
     * 超级会员价
     */
    private BigDecimal superVipPrice;

    /**
     * 备注
     */
    private String remark;
}
