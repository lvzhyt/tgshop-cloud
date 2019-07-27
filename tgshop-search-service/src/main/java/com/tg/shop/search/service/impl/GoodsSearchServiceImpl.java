package com.tg.shop.search.service.impl;


import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.product.info.GoodsInfo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.entity.vo.EsGoodsSearchVo;
import com.tg.shop.search.feign.service.FeignProductService;
import com.tg.shop.search.repositry.GoodsRepository;
import com.tg.shop.search.service.GoodsSearchService;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSearchServiceImpl implements GoodsSearchService {

    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private FeignProductService feignProductService;

    @Override
    public Page<EsGoods> searchGoods(String search, int pageNum, int pageSize) {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 评论数
        ScoreFunctionBuilder<?> scoreFunctionBuilder = ScoreFunctionBuilders.fieldValueFactorFunction("commentNum").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(0.1f);
        FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(scoreFunctionBuilder).boostMode(CombineFunction.SUM);
        queryBuilder.withQuery(QueryBuilders.matchQuery("goodsName",search));
        queryBuilder.withQuery(query).build();
        SearchQuery searchQuery = queryBuilder.build();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        searchQuery.setPageable(pageable);
        Page<EsGoods>  page = goodsRepository.search(searchQuery);

        return page;
    }

    @Override
    public Page<EsGoods> searchGoods(String search, int sortType,int pageNum,int pageSize) {
        return null;
    }

    @Override
    public Page<EsGoods> searchGoods(String search, EsGoodsSearchVo searchCondition, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<EsGoods> searchGoods(String search, double minPrice, double maxPrice,int pageNum,int pageSize) {
        return null;
    }

    @Override
    public Page<EsGoods> searchGoods(String search, GeoPoint location, double offset,int pageNum,int pageSize) {
        return null;
    }

    @Override
    public ResultObject updateGoodsIndex(String goodsId) {
        ResultObject<Goods> resultObject = feignProductService.getGoodsById(goodsId);
        if(resultObject.getResult()==0){
            return resultObject;
        }
        Goods goods = (Goods) resultObject.getData();
        if(goods==null){
            return new ResultObject("8001","商品不存在.goodsId:"+goodsId );
        }
        ResultObject<List<GoodsSkuDetailResultVo>> skuResultObject = feignProductService.getSkuDetailListByGoodsId(goods.getGoodsId());
        if(skuResultObject.getResult()==0){
            return skuResultObject;
        }
        List<GoodsSkuDetailResultVo> list = skuResultObject.getData();
        for (GoodsSkuDetailResultVo skuVo :
                list) {
            EsGoods esGoods = new EsGoods();
            BeanUtils.copyProperties(goods,esGoods);
            // 价格 库存
            BeanUtils.copyProperties(skuVo,esGoods);
            // 销量
            esGoods.setSaleNum(0);
            // 评价数
            esGoods.setCommentNum(0);

            // 商品索引
            if(goods.getGoodsStatus()== GoodsInfo.STATUS_SHOW_ON){
                goodsRepository.save(esGoods);
            }else {
                if(goodsRepository.existsById(esGoods.getSkuId())){
                    goodsRepository.delete(esGoods);
                }
            }
        }
        return new ResultObject();
    }
}
