package controller;

import dto.ProductDto;
import entity.Product;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")  // DÙNG SỐ NHIỀU
public class ProductController {

    private final ProductService svc;

    public ProductController(ProductService svc) {
        this.svc = svc;
    }

    // GET /products/list : USER & ADMIN xem
    @GetMapping("/list")
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Product> data = svc.searchByName(q);
        model.addAttribute("products", data);
        model.addAttribute("q", q);
        return "products/list";
    }

    // GET /products/add : ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductDto()); // ĐÚNG: 'product' (không phải 'products')
        return "products/new_product";
    }

    // POST /products/save : ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") ProductDto dto, BindingResult br) {
        if (br.hasErrors()) return "products/new_product";
        svc.save(dto);
        return "redirect:/products/list";  // ĐÚNG: /products
    }

    // GET /products/edit/{id} : ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Product p = svc.get(id);
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setBrand(p.getBrand());
        dto.setMadein(p.getMadein());
        dto.setPrice(p.getPrice());
        model.addAttribute("product", dto);
        return "products/edit_product";
    }

    // POST /products/update/{id} : ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("product") ProductDto dto,
                         BindingResult br) {
        if (br.hasErrors()) return "products/edit_product";
        svc.update(id, dto);
        return "redirect:/products/list";  // ĐÚNG: /products
    }

    // GET /products/delete/{id} : ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        svc.delete(id);
        return "redirect:/products/list";  // ĐÚNG: /products
    }
}
