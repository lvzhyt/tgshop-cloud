package com.tg.shop.categories.controller;

import com.github.pagehelper.PageInfo;
import com.tg.shop.categories.service.BrandService;
import com.tg.shop.core.domain.categories.entity.Brand;
import com.tg.shop.core.entity.ResultObject;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/findBrandPageList")
    public ResultObject<PageInfo<Brand>> findBrandPageList(Brand brand, int pageNum, int pageSize){
        PageInfo<Brand> pageInfo = brandService.findBrandPageList(brand, pageNum, pageSize);
        return new ResultObject<>(pageInfo);
    }

    @ApiOperation(value = "searchBrand",tags = {"查找品牌"})
    @GetMapping("/searchBrand")
    public ResultObject<List<Brand>> searchBrand(@RequestParam String brandName){
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        List<Brand> list = brandService.findBrandList(brand);
        return new ResultObject(list);
    }

}
