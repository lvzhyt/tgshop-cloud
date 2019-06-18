package com.tg.shop.core.domain.product.info;

public interface GoodsInfo {

//    商品状态,1: 未发布，2：待审核，3：审核驳回，4：待上架，5：在售，6：已下架，7：锁定， 8： 申请解锁
    /**
     * 未发布
     */
    int STATUS_EDIT = 1;

    /**
     * 待审核
     */
    int STATUS_AUDIT_WAIT = 2;

    /**
     * 审核驳回
     */
    int STATUS_AUDIT_REJECT = 3;

    /**
     * 待上架
     */
    int STATUS_SHOW_WAIT = 4;

    /**
     * 在售
     */
    int STATUS_SHOW_ON = 5;

    /**
     * 已下架
     */
    int STATUS_SHOW_OFF = 6;

    /**
     * 锁定
     */
    int STATUS_LOCK = 7;

    /**
     * 申请解锁
     */
    int STATUS_APPLY_UNLOCK = 8;


}
