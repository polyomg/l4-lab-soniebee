package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // <-- SỬA Ở ĐÂY
import org.springframework.data.domain.PageRequest; // <-- THÊM VÀO
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;


@Controller
public class ProductController {
    @Autowired
    ProductDAO dao;

    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        Sort sort = Sort.by(Sort.Direction.DESC, field.orElse("price"));
        model.addAttribute("field", field.orElse("price").toUpperCase());
        model.addAttribute("items", dao.findAll(sort));
        return "product/sort";
    }

    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        int pageIndex = p.orElse(0);
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        Pageable pageable = PageRequest.of(pageIndex, 5); // <-- SỬA Ở ĐÂY
        model.addAttribute("page", dao.findAll(pageable));
        return "product/page";
    }
    
    @RequestMapping("/product/search-and-page")
    public String searchAndPage(Model model, 
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field) {
        
        String sortField = field.orElse("price");
        Sort sort = Sort.by(Sort.Direction.ASC, sortField);

        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort); // <-- SỬA Ở ĐÂY
        Page<Product> page = dao.findAll(pageable);
        
        model.addAttribute("page", page);
        model.addAttribute("field", sortField);
        
        return "product/search-and-page";
    }
}