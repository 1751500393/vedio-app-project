package com.cen.cate.service.impl;

import com.cen.cate.entity.Category;
import com.cen.cate.dao.CategoryDao;
import com.cen.cate.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 分类(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-06-05 19:47:17
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return this.categoryDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param category 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Category> queryByPage(Category category, PageRequest pageRequest) {
        long total = this.categoryDao.count(category);
        return new PageImpl<>(this.categoryDao.queryAllByLimit(category, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        this.categoryDao.insert(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.categoryDao.deleteById(id) > 0;
    }

    @Override
    public List<Category> queryByFirstLevel() {
        return categoryDao.selectByFirstLevel();
    }
}
