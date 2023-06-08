package com.cen.cate.controller;

import com.cen.cate.entity.Category;
import com.cen.cate.service.CategoryService;
//import com.cen.commans.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类(Category)表控制层
 *
 * @author makejava
 * @since 2023-06-05 19:47:08
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private static final Logger log= LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
//查询类别
    @GetMapping
    public List<Category> categoryList() {
        return categoryService.queryByFirstLevel();
    }
//更新类别
    @PatchMapping("/{id}")
    public Category update(@PathVariable("id") Integer id, @RequestBody Category category) {
        log.info("更新类别的id:{}",id);
//        log.info("更新类别信息:{}", JSONUtils.writeJSON(category));
        category.setId(id);
       return categoryService.update(category);
    }
//    添加类别接口
    @PostMapping
    public Category save(@RequestBody Category category){
//        log.info("添加类别信息:{}",JSONUtils.writeJSON(category));
         Category categoryDB=categoryService.insert(category);
        return  categoryDB;
    }
//    删除列表
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        log.info("删除分类对象:{}",+id);
        categoryService.deleteById(id);


    }
}

