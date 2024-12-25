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
public void updateProduct(Long id,ProductModel productModel) {
	  
    Optional<ProductEntity> optionalData = productRepository.findById(id);     
    if (optionalData.isPresent()) {
   
        ProductEntity entity = optionalData.get();

       
        double stockValue = productModel.getPrice() * productModel.getQuantity();
        double discountPrice = productModel.getPrice() * productModel.getDiscountrate() / 100;
        double offerPrice = productModel.getPrice() - discountPrice;
        double taxPrice = offerPrice * 18 / 100; 
        double finalPrice = offerPrice + taxPrice;

       
        entity.setName(productModel.getName());
        entity.setBrand(productModel.getBrand());
        entity.setMadeIn(productModel.getMadeIn());
        entity.setPrice(productModel.getPrice());
        entity.setQuantity(productModel.getQuantity());
        entity.setDiscountrate(productModel.getDiscountrate());
        entity.setStockValue(stockValue);
        entity.setDiscountprice(discountPrice);
        entity.setOfferPrice(offerPrice);
        entity.setTaxprice(taxPrice);
        entity.setFinalPrice(finalPrice);

    
        productRepository.save(entity);
    } 


}



}