package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class BatchDeleteGoodsParameter {

    @NotEmpty
    private String[] goodsIds;

}
