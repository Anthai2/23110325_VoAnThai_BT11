// src/main/java/entity/Product.java
package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(255)")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    @NotBlank(message = "Brand is required")
    private String brand;

    @Column(name="madein", columnDefinition = "nvarchar(255)")
    @NotBlank(message = "Made in is required")
    private String madein;

    @Positive(message = "Price must be > 0")
    private float price;
}
