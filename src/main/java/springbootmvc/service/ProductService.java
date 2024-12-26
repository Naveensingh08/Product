package springbootmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootmvc.entity.ProductEntity;
import springbootmvc.repository.ProductRepository;
import springbootmvcmodel.ProductModel;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public void servicemethod(ProductModel productModel) {
		// TODO Auto-generated method stub
		ProductEntity productEntity=new ProductEntity();
		
		double offerprice=productModel.getPrice()-((productEntity.getDiscount()/100)*productModel.getPrice());
		double taxPrice=(productEntity.getTax()/100)*offerprice;
		
		double finalPrice=offerprice+taxPrice;
		
		productEntity.setName(productModel.getName());
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadeIn(productModel.getMadeIn());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setOfferPrice(offerprice);
		productEntity.setTaxPrice(taxPrice);
		productEntity.setFinalPrice(finalPrice);
		
		productRepository.save(productEntity);
		
	}
	
	
	public List<ProductEntity> getProductList()
	{
		List<ProductEntity> product=productRepository.findAll();
		return product;
	}


	public void deleteProductByID(Long id) {
		
		productRepository.deleteById(id);
		
	}


	public ProductEntity getProductById(Long id) {
		
		Optional<ProductEntity> productData=productRepository.findById(id);
		
		if(productData.isPresent())
		{
			ProductEntity product=productData.get();
			return product;
		}
		else
		{
			return null;
		}
	}


	public ProductEntity getEditedData(Long id) {
		// TODO Auto-generated method stub
		Optional<ProductEntity> productEntity=productRepository.findById(id);
		
		if(productEntity.isPresent())
		{
			ProductEntity product=productEntity.get();
			return product;
		}
		
		else
		{
			return null;
		}
	}


	public void productUpdateData(Long id,ProductModel productModel) {
		// TODO Auto-generated method stub
		Optional<ProductEntity> productData=productRepository.findById(id);
		
		if(productData.isPresent())
		{
			ProductEntity productentity=productData.get();
			
			productentity.setName(productModel.getName());
			productentity.setBrand(productModel.getBrand());
			productentity.setMadeIn(productModel.getMadeIn());
			productentity.setPrice(productModel.getPrice());
			productentity.setQuantity(productModel.getQuantity());
			
			productRepository.save(productentity);
		}
		else
		{
			System.out.println("null");
		}
	
		
	}


	

	
}


