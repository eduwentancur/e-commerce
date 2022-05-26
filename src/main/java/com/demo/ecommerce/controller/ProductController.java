
package com.demo.ecommerce.controller;

import com.demo.ecommerce.models.Product;
import com.demo.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository productRepo;
    
    
    @GetMapping("/listProducts.html")
    public String mostrarEjemplo(Model model){
        
        
        
        return "listProducts";
        
    }
    
    @GetMapping("/")
    public String showProducts(){
    
        return "addProducts";
    
    }
    
    @PostMapping("/addP")
    public String saveProduct(@RequestParam("pname") String name,@RequestParam("price") Integer price,@RequestParam("desc") String description){
        Product producto = new Product();
        producto.setName(name);
        producto.setPrice(price);
        producto.setDescription(description);
        productRepo.save(producto);
        
        
        return "redirect:/listProducts.html";
    
    }
    
}