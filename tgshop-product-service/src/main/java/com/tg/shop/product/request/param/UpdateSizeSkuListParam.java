package com.tg.shop.product.request.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel
public class UpdateSizeSkuListParam {

    @ApiModelProperty
    @NotEmpty
    private String goodsId;
    @NotEmpty
    private String colorAttrValId;

    private List<String> sizeAttrValIds;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getColorAttrValId() {
        return colorAttrValId;
    }

    public void setColorAttrValId(String colorAttrValId) {
        this.colorAttrValId = colorAttrValId;
    }

    public List<String> getSizeAttrValIds() {
        return sizeAttrValIds;
    }

    public void setSizeAttrValIds(List<String> sizeAttrValIds) {
        this.sizeAttrValIds = sizeAttrValIds;
    }
}
