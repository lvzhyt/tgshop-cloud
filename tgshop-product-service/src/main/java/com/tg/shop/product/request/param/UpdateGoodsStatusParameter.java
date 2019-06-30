package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UpdateGoodsStatusParameter {

    @NotEmpty
    private String goodsId;

    @NotNull
    private Integer goodsStatus;
}
