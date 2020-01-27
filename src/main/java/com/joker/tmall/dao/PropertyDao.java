package com.joker.tmall.dao;

import com.joker.tmall.pojo.Category;
import com.joker.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jokerpz
 * @version 1.0
 * @date 2020/1/25 9:22 下午
 */
public interface PropertyDao extends JpaRepository<Property, Integer> {
    Page<Property> findByCategory(Category category, Pageable pageable);
}
