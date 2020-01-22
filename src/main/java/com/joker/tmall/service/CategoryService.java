package com.joker.tmall.service;

import com.joker.tmall.dao.CategoryDao;
import com.joker.tmall.pojo.Category;
import com.joker.tmall.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    private static Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);


    /**
     * 分类-查询
     * @return
     */
    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return categoryDao.findAll(sort);
    }

    /**
     * 分类-分页查询
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    public Page4Navigator<Category> listByPage(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Category> pageFromJPA =categoryDao.findAll(pageable);
        return new Page4Navigator<Category>(pageFromJPA, navigatePages);
    }

    /**
     * 分类-新增
     * @param category
     */
    public void addCategory(Category category){
        categoryDao.save(category);
    }

    public void deleteCategory(Integer id) {
        if (id == null || id == 0) {
            LOGGER.error("传入分类的id为{}", id);
            throw new NullPointerException("删除操作有误");
        }
        categoryDao.deleteById(id);
    }
}
