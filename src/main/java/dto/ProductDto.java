
package dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Made in is required")
    private String madein;

    @Positive(message = "Price must be > 0")
    private float price;
}
