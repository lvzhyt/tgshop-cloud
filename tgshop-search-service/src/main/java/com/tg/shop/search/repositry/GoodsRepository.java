package com.tg.shop.search.repositry;

import com.tg.shop.search.entity.EsGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface GoodsRepository extends ElasticsearchRepository<EsGoods,String> {

}
