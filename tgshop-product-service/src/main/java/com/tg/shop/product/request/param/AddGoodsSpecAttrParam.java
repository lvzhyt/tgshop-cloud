package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AddGoodsSpecAttrParam {

    @ApiModelProperty
    @NotEmpty
    private String goodsId;

    @NotEmpty
    @ApiModelProperty
    @Length(max = 50,message = "最大长度50")
    private String attrValue;

    @NotNull
    private Integer sortNum;

    /**
     * 属性等级 1, 2
     */
    @NotNull
    private Integer attrLevel;
}
