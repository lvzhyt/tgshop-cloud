package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UpdateSkuStatusParameter {

    @NotEmpty
    @ApiModelProperty
    private String skuId;

    @NotNull
    @ApiModelProperty
    private Integer skuStatus;
}
