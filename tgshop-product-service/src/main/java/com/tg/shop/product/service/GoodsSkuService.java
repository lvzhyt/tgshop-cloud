package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuInventoryResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuPriceResultVo;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/20 16:47
 */
public interface GoodsSkuService {

    GoodsSku getBySkuId(String id);

    int saveSku(GoodsSku goodsSku);

    int deleteSku(GoodsSku goodsSku);

    int updateSkuById(GoodsSku goodsSku);

    /**
     * 根据商品查找
     * @param goodsId
     * @return
     */
    List<GoodsSku> findSkuByGoodsId(String goodsId);

    /**
     * 条件查找
     * @param condition
     * @return
     */
    List<GoodsSku> findSkuByCondition(GoodsSku condition);

    GoodsSku findUniqueBySkuNo(String skuNo);

    /**
     * 批量创建 SKU
     * @param newList 新增
     * @param updateAddList  更新增加 is_del = 0
     * @param delList 删除 is_del = 1
     * @return
     */
    int batchCreateAndDelSku(List<GoodsSku> newList, List<GoodsSku> updateAddList, List<GoodsSku> delList);


    /**
     * 查询商品库存
     * @param goodsId
     * @return
     */
    List<GoodsSkuInventoryResultVo> findSkuInventoryListByGoodsId(String goodsId);

    /**
     * 查询商品价格
     * @param goodsId
     * @return
     */
    List<GoodsSkuPriceResultVo> findSkuPriceListByGoodsId(String goodsId);

    /**
     * 查询商品详细列表
     * @param goodsId
     * @return
     */
    List<GoodsSkuDetailResultVo> findSkuDetailListByGoodsId(String goodsId);
}
