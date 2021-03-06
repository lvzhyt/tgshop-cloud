package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class BatchUpdateGoodsStatusParameter {

    @NotEmpty
    private String[] goodsIds;

    @NotNull
    private Integer goodsStatus;
}
