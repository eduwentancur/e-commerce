package com.demo.ecommerce.service;

import com.demo.ecommerce.models.Product;
import com.demo.ecommerce.repository.ProductRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo;
    
    public void saveProductToDB(MultipartFile file, String name,String description,int price){
    
        Product producto = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }
        try {
            producto.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        producto.setDescription(description);
        
        producto.setName(name);
        producto.setPrice(price);
        
        productRepo.save(producto);
        
    }
    
}
