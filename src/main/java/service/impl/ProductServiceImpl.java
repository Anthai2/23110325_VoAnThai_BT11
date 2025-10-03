// src/main/java/service/impl/ProductServiceImpl.java
package service.impl;

import dto.ProductDto;
import entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;
import service.ProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> listAll() {
        return repo.findAll();
    }

    @Override
    public List<Product> searchByName(String q) {
        return (q == null || q.isBlank()) ? repo.findAll()
                : repo.findByNameContainingIgnoreCase(q.trim());
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Override
    public Product save(ProductDto dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setBrand(dto.getBrand());
        p.setMadein(dto.getMadein());
        p.setPrice(dto.getPrice());
        return repo.save(p);
    }

    @Override
    public Product update(Long id, ProductDto dto) {
        Product p = get(id);
        p.setName(dto.getName());
        p.setBrand(dto.getBrand());
        p.setMadein(dto.getMadein());
        p.setPrice(dto.getPrice());
        return repo.save(p);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
