
package com.demo.ecommerce.controller;

import com.demo.ecommerce.models.Product;
import com.demo.ecommerce.repository.ProductRepository;
import com.demo.ecommerce.service.ProductService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public String home(){
        
        
        
        
        return "index";
    
    }
    
    @GetMapping("/addProducts.html")
    public String showProducts(){
        
        
        
        
        return "addProducts";
    
    }
    
    
    @GetMapping("/listProducts.html")
    public String mostrarEjemplo(Model model){
        
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        
        
        return "listProducts";
        
    }
    
   
    
    @PostMapping("/addP")
    public String saveProduct(@RequestParam("file") MultipartFile file, @RequestParam("pname") String name,@RequestParam("price") Integer price,@RequestParam("desc") String description){
        productService.saveProductToDB(file, name, description, price);
        return "redirect:/listProducts.html";
    }
    
    
    @GetMapping("/deleteProd/{id}" )
    public String deleteProduct(@PathVariable("id") Long id){
        Product producto = productRepo.findById(id).get();
        productRepo.delete(producto);
        
        
        return "redirect:/listProducts.html";
    
    }
    
    @PostMapping("/changeName")
    public String changePname(@RequestParam("id") Long id, @RequestParam("newPname") String name){
        
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setName(name);
        productRepo.save(p);
        return "redirect:/listProducts.html";
    }
    
}
