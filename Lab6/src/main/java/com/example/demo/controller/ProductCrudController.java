package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@Controller
public class ProductCrudController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CategoryDAO categoryDAO;

    // Hiển thị form và danh sách sản phẩm
    @RequestMapping("/product/crud/index")
    public String index(Model model) {
        Product item = new Product();
        model.addAttribute("item", item);
        loadData(model);
        return "product/crud/index";
    }

    // Xử lý nút Edit
    @RequestMapping("/product/crud/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Product item = productDAO.findById(id).orElse(new Product());
        model.addAttribute("item", item);
        loadData(model);
        return "product/crud/index";
    }

    // Xử lý nút Create
    @RequestMapping("/product/crud/create")
    public String create(Product item) {
        productDAO.save(item);
        return "redirect:/product/crud/index";
    }

    // Xử lý nút Update
    @RequestMapping("/product/crud/update")
    public String update(Product item) {
        productDAO.save(item);
        return "redirect:/product/crud/edit/" + item.getId();
    }

    // Xử lý nút Delete
    @RequestMapping("/product/crud/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productDAO.deleteById(id);
        return "redirect:/product/crud/index";
    }
    
    // Hàm dùng chung để load danh sách Product và Category
    private void loadData(Model model) {
        List<Product> items = productDAO.findAll();
        model.addAttribute("items", items);
        
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
    }
}