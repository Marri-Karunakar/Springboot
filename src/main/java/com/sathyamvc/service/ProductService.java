package com.sathyamvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sathyamvc.entity.ProductEntity;
import com.sathyamvc.model.ProductModel;
import com.sathyamvc.repositary.ProductRepository;
@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;
  public void SaveProductDetails(ProductModel productModel) {
     double taxprice;
     taxprice=productModel.getPrice()*18/100;
     
     double discountprice;
     discountprice=productModel.getPrice()*productModel.getDiscountrate()/100;
     double offerPrice;
     offerPrice=productModel.getPrice()-discountprice;
     double finalPrice;
     finalPrice=offerPrice+taxprice;
     double stockValue;
     stockValue=finalPrice*productModel.getDiscountrate();
     
     ProductEntity productEntity=new ProductEntity();
     productEntity.setName(productModel.getName());
     productEntity.setBrand(productModel.getBrand());
     productEntity.setMadeIn(productModel.getMadeIn());
     productEntity.setPrice(productModel.getPrice());
     productEntity.setDiscountrate(productModel.getDiscountrate());
     productEntity.setQuantity(productModel.getQuantity());

     productEntity.setDiscountprice(discountprice);
     productEntity.setOfferPrice(offerPrice);
     productEntity.setFinalPrice(finalPrice);
     productEntity.setTaxprice(taxprice);
     productEntity.setStockValue(stockValue);
     
     productRepository.save(productEntity);
  }
  public List<ProductEntity> getallproducts(Model model){
    List<ProductEntity> products=productRepository.findAll();
    return products;
    
  }
public ProductEntity SearchbyId(Long id) {
	Optional<ProductEntity> optionalData=productRepository.findById(id);
	
	if(optionalData.isPresent()) {
		ProductEntity product=optionalData.get();
		return product;
	}
	else {
		return null;
	}
}
public void deleteProductbyId(Long id) {
		productRepository.deleteById(id);
	}
//edit
public ProductModel editProductById1( Long id) {
Optional<ProductEntity> optionalData=productRepository.findById(id);
if(optionalData.isPresent()) {
 ProductEntity productEntity=optionalData.get();
 ProductModel productModel=new ProductModel();
 productModel.setName(productEntity.getName());
 productModel.setBrand(productEntity.getBrand());
 productModel.setMadeIn(productEntity.getMadeIn());
 productModel.setPrice(productEntity.getPrice());
 productModel.setQuantity(productEntity.getQuantity());
 productModel.setDiscountrate(productEntity.getDiscountrate());
 return productModel;
}
else {
 return null;
}
}
//updateing the form

public ProductModel  editProductById(Long id) {
    Optional<ProductEntity> optionalData= productRepository.findById(id);
    if(optionalData.isPresent())
    {
      ProductEntity product = optionalData.get();
      
      ProductModel productModel = new ProductModel();
      productModel.setName(product.getName());
      productModel.setBrand(product.getBrand());
      productModel.setMadeIn(product.getMadeIn());
      productModel.setPrice(product.getPrice());
      productModel.setQuantity(product.getQuantity());
      productModel.setDiscountrate(product.getDiscountrate());
      return productModel;
    }
    else {
      return null;
    }
  }
  
  public void updateProductById(Long id, ProductModel updatedProductModel, Model model) {
      Optional<ProductEntity> optionalData = productRepository.findById(id);
      if (optionalData.isPresent()) {
          ProductEntity product = optionalData.get();

          // Update product entity with new values from the model
          product.setName(updatedProductModel.getName());
          product.setBrand(updatedProductModel.getBrand());
          product.setMadeIn(updatedProductModel.getMadeIn());
          product.setPrice(updatedProductModel.getPrice());
          product.setQuantity(updatedProductModel.getQuantity());
          product.setDiscountrate(updatedProductModel.getDiscountrate());

          // Save the updated product
          ProductEntity updatedProduct = productRepository.save(product);

          // Map updated product to ProductModel (if needed for further use)
          ProductModel productModel = new ProductModel();
          productModel.setName(updatedProduct.getName());
          productModel.setBrand(updatedProduct.getBrand());
          productModel.setMadeIn(updatedProduct.getMadeIn());
          productModel.setPrice(updatedProduct.getPrice());
          productModel.setQuantity(updatedProduct.getQuantity());
          productModel.setDiscountrate(updatedProduct.getDiscountrate());

          // Add updated ProductModel to the Model for the view
          model.addAttribute("updatedProduct", productModel);
      } else {
          // Handle the case when the product with the given ID does not exist
          model.addAttribute("errorMessage", "Product not found with ID: " + id);
      }
  }

}