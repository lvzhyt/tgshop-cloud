package com.tg.shop.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "brand",type = "doc")
public class EsBrand {

    @Id
    private String id;

    @Field
    private String name;
}
