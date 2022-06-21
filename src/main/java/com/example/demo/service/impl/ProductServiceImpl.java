package com.example.demo.service.impl;

import com.example.demo.helper.ProductHelper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.experimental.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(MultipartFile multipartFile) {
        try {
            List<Product> products = ProductHelper.convertExcelToListOfProduct(multipartFile.getInputStream());
            this.productRepository.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}
