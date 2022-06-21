package com.example.demo.controller;

import com.example.demo.helper.ProductHelper;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("multipartFile")MultipartFile multipartFile){
        if(ProductHelper.checkExcelFormat(multipartFile)){
            this.productService.save(multipartFile);
            return  ResponseEntity.ok("File uploaded and data saved");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
    }
    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }

}
