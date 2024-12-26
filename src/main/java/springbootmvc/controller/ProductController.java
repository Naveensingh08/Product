package springbootmvc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import springbootmvc.entity.ProductEntity;
import springbootmvc.service.ProductService;
import springbootmvcmodel.ProductModel;

@Controller
public class ProductController {
	
@Autowired
	ProductService productService;
	
	@GetMapping("/ProdForm")
	
	public String getProductForm( Model model)
	{
		ProductModel obj=new ProductModel();
		obj.setMadeIn("india");
		model.addAttribute("productModel",obj);
		return "AddProductForm";
	}
	
	
	
	@PostMapping("/getProductForm")
	
	public String fillProductForm(@Valid ProductModel productModel,BindingResult bindingResult,Model model)
	{
		HashMap<String,String>  validationErrors=new HashMap<String,String> ();
		
		if(bindingResult.hasErrors())
		{
			for(FieldError fieldError : bindingResult.getFieldErrors())
			{
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
				
			}
			
			model.addAttribute("validationErrors",validationErrors);
			return "AddProductForm";
		}
		productService.servicemethod(productModel);
		return "redirect:/getAllProduct";
	}
	
	
	@GetMapping("/getAllProduct")
	public String getProductList(Model model)
	{
		List<ProductEntity> products=productService.getProductList();
		model.addAttribute("products",products);
		return "ProductDetail";
	
		
	}
	
	@GetMapping("/delete/{id}")
	
	public String deleteProductById(@PathVariable("id") Long id)
	{
		productService.deleteProductByID(id);
		return "redirect:/getAllProduct";
	}

	
	
	
	@GetMapping("/getId")
	public String getSearchForm()
	{
		return "Search";
	}
	
	@PostMapping("/searchByID")
	
	public String getProductById(@RequestParam Long id,Model model)
	{
		ProductEntity products=productService.getProductById(id);
		model.addAttribute("products",products);
		return "Search";
	}
	
	
	@GetMapping("/FormEdit")
	
	public String getEditForm()
	{
		return "EditForm";
	}
	
	
@GetMapping("/edit/{id}")
	
	public String editProductById(@PathVariable("id") Long id,Model model)
	{
	
		ProductEntity product=productService.getEditedData(id);
		model.addAttribute("product",product);
		return "EditForm";
	}

@PostMapping("/getEditForm/{id}")

public String updateData(@PathVariable("id") Long id,Model model,ProductModel productModel)
{
	productService.productUpdateData(id,productModel);
	return "redirect:/getAllProduct";
}

}
