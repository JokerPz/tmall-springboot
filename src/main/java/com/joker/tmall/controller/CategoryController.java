package com.joker.tmall.controller;

import com.joker.tmall.pojo.Category;
import com.joker.tmall.service.CategoryService;
import com.joker.tmall.util.ImageUtil;
import com.joker.tmall.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    private static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);


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
        categoryService.addOrUpdateCategory(category);
        // 图片服务器
        addOrUpdateImageFile(image, request, category);
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
    private void addOrUpdateImageFile(MultipartFile image, HttpServletRequest request, Category category) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    /**
     * 分类-删除
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping(value = "/categories/{id}")
    public String deleteCategory(@PathVariable(value = "id")Integer id, HttpServletRequest request) {
        categoryService.deleteCategory(id);
        File imageFolder = new File(request.getServletContext().getRealPath("/img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        return null;
    }

    /**
     * 查询单个分类
     * @param id
     * @return
     */
    @GetMapping(value = "/categories/{id}")
    public Category getCategory(@PathVariable(value = "id")Integer id) {
        return categoryService.getCategory(id);
    }

    @PutMapping(value = "/categories/{id}")
    public Category updateCatogory(Category category, MultipartFile image, HttpServletRequest request) throws IOException {
        if (category == null ) {
            throw new NullPointerException("修改内容为空");
        }
        LOGGER.info("入参：【{}】", category.toString());
        categoryService.addOrUpdateCategory(category);
        if (image != null) {
            addOrUpdateImageFile(image, request, category);
        }
        return category;
    }
}
