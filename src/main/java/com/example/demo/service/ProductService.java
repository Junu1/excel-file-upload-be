package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {
    public void save(MultipartFile multipartFile);

    public List<Product> getAllProducts();

}
