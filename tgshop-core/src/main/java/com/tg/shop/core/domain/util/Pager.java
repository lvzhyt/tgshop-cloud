package com.tg.shop.core.domain.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {

    private int pageNum;
    private int pageSize;
    private int pages;
    @JsonSerialize(using= ToStringSerializer.class)
    private long total;
}
