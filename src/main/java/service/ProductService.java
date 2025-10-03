
package service;

import dto.ProductDto;
import entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAll();
    List<Product> searchByName(String q);
    Product get(Long id);
    Product save(ProductDto dto);
    Product update(Long id, ProductDto dto);
    void delete(Long id);
}
