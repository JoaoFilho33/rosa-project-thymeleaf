package com.projeto.rosa.controller;

import com.projeto.rosa.dto.ProductDto;
import com.projeto.rosa.model.Product;
import com.projeto.rosa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> productsList = productService.listProducts();
        model.addAttribute("productsList", productsList);
        return "products";
    }

    @GetMapping("/products/create")
    public String createProductForm(Model model) {
        ProductDto product = new ProductDto();
        model.addAttribute("product", product);
        return "create";
    }

    @PostMapping("/products/create")
    public String createProduct(@ModelAttribute(value = "product") ProductDto product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/products/changeStatus/{id}")
    public String changeProductStatus(@PathVariable Long id) {
        productService.changeProductStatus(id);
        return "redirect:/products";
    }

}
