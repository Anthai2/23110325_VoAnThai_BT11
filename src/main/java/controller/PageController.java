package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// controller/PageController.java
@Controller
public class PageController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/admin")
    public String adminHome() { return "admin"; }

    @GetMapping("/user")
    public String userHome() { return "user"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/admin/products")
    public String adminProducts() {
        return "redirect:/products/list";
    }

    @GetMapping("/admin/products/new")
    public String adminProductsNew() {
        return "redirect:/products/add";
    }
}
