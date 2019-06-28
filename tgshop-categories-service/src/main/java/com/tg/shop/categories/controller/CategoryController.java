package com.tg.shop.categories.controller;

import com.tg.shop.categories.service.CategoryService;
import com.tg.shop.core.domain.categories.entity.Category;
import com.tg.shop.core.entity.ResultObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "findListByParentIdUrl", tags = "子类目查询")
    @GetMapping("/findListByParentId")
    public ResultObject<List<Category>> findListByParentId(@RequestParam(required = false) String parentId){
        Category condition = new Category();
        condition.setParentCategoryId(parentId);
        List<Category> list = categoryService.findCategoryList(condition);
        return new ResultObject<>(list);
    }
}
