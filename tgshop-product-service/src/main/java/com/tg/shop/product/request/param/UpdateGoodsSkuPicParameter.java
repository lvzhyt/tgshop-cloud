package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel
public class UpdateGoodsSkuPicParameter {

    @NotEmpty
    private String goodsId;

    @NotEmpty
    private String[] skuIds;

    @NotEmpty
    @Size(min = 1,max = 5)
    private String[] picIds;

    @NotNull
    private Integer type;

}
