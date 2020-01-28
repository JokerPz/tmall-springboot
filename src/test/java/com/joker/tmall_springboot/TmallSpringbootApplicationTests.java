package com.joker.tmall_springboot;

import com.joker.tmall.dao.CategoryDao;
import com.joker.tmall.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


class TmallSpringbootApplicationTests extends BaseTest{

    @Autowired
    CategoryDao categoryDao;

    @Test
    void contextLoads() {
        List<Category> categoryList = categoryDao.findAll();
//        for (Category category : categoryList) {
//            System.out.println(category);
//        }
    }

    @Test
    void addCategory() {
//        for (int i = 1; i <= 15; i++) {
//            Category category = new Category();
//            category.setName(String.format("小米%s手机", i));
//            category = categoryDao.save(category);
//            System.out.println(category);
//        }
    }

    @Test
    void deleleCategory() {
        Category deleteCategory = new Category();
        deleteCategory.setId(3);
//        categoryDao.deleteById(3);
    }



}
