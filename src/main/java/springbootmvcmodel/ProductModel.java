package springbootmvcmodel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@NotBlank(message = "Brand cannot be blank")
	private String brand;
	@NotBlank(message = "MadeIn cannot be blank")
	private String madeIn;
	
	@Positive(message = "Price must be possitive")
	private double price;
	
	@Min(value = 1, message = "Quantity at least one")
	private int quantity;

}
