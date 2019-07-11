package com.tg.shop.product.request.param;

import javax.validation.constraints.NotEmpty;

public class DeleteGoodsSpecAttrParameter {

    @NotEmpty
    private String goodsId;

    @NotEmpty
    private String tbId;

    public DeleteGoodsSpecAttrParameter() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTbId() {
        return tbId;
    }

    public void setTbId(String tbId) {
        this.tbId = tbId;
    }
}
