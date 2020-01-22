package com.joker.tmall.controller;

import com.joker.tmall.dao.CategoryDao;
import com.joker.tmall.pojo.Category;
import com.joker.tmall.service.CategoryService;
import com.joker.tmall.util.ImageUtil;
import com.joker.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 分页查询分类列表
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> listCategory(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        return categoryService.listByPage(start, size, 5);
    }

    /**
     * 新增分类
     * @param image
     * @param request
     * @param category
     * @return
     * @throws IOException
     */
    @PostMapping("/categories")
    public Category addCategory(MultipartFile image, HttpServletRequest request, Category category) throws IOException {
        // 数据入库
        categoryService.addCategory(category);
        // 图片服务器
        saveOrUpdateImageFile(image, request, category);
        // 返回新的id
        return category;
    }

    /**
     * 把图片存进工程里的指定文件夹
     * @param image
     * @param request
     * @param category
     * @throws IOException
     */
    private void saveOrUpdateImageFile(MultipartFile image, HttpServletRequest request, Category category) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

}
