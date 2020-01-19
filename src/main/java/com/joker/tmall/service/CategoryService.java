package com.joker.tmall.service;

import com.joker.tmall.dao.CategoryDao;
import com.joker.tmall.pojo.Category;
import com.joker.tmall.util.Page4Navigator;
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

    /**
     * 分类-类别查询
     * @return
     */
    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return categoryDao.findAll(sort);
    }

    public Page4Navigator<Category> listByPage(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Category> pageFromJPA =categoryDao.findAll(pageable);
        return new Page4Navigator<Category>(pageFromJPA, navigatePages);
    }
}
