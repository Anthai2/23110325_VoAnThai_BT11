package controller;

import model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final List<Customer> customers = List.of(
            Customer.builder().id("001").name("Nguyễn Hữu Trung")
                    .email("trungnhspkt@gmail.com").phoneNumber("0900000001").build(),
            Customer.builder().id("002").name("Hữu Trung")
                    .email("trunghuu@gmail.com").phoneNumber("0900000002").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                customers.stream()
                        .filter(c -> c.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Not found: " + id))
        );
    }
}
