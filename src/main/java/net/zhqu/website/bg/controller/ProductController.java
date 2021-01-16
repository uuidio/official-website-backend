package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.ProductCategoryModel;
import net.zhqu.website.bg.model.ProductModel;
import net.zhqu.website.bg.service.ProductCategoryService;
import net.zhqu.website.bg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-20 16:52
 */
@Slf4j
@Controller
@RequestMapping("/bg/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/v/index.html")
    public String index() {
        return "bg/product_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        Page pageObj = Page.builder().currentPage(page).pageSize(limit).build();
        try {
            Param param = Param.builder().build().add("page", pageObj);
            List<ProductModel> list = productService.findAllPage(param);
            return BaseResultUtil.success(pageObj, list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/{id}")
    public String update(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id", id);
        try {
            ProductModel productModel = productService.findOne(param);

            model.addAttribute("product", productModel);

            List<ProductCategoryModel> categoryModels = productCategoryService.findAll();
            model.addAttribute("categories", categoryModels);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/product_add";
    }


    @GetMapping("/v/add.html")
    public String add(Model model) {
        List<ProductCategoryModel> categoryModels = productCategoryService.findAll();
        model.addAttribute("categories", categoryModels);

        return "bg/product_add";
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody ProductModel productModel) {
//        List<OneKeyLabelModel> oneKeyLabelModels = articleModel.getOneKeyLabel();
        try {
            if (productModel.getId() != null) {
                productService.update(productModel);
            } else {

                productService.insert(productModel);
            }
            return BaseResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResultUtil.fail("异常");
        }
    }

    @DeleteMapping("/d/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id) {
        Param param = Param.builder().build().add("id", id);
        try {
            productService.delete(param);
            return BaseResultUtil.success(null);
        } catch (CURDException e) {
            e.printStackTrace();
            return BaseResultUtil.fail("删除失败");
        }
    }


}
