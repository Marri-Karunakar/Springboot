package com.sathyamvc.controller;

import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sathyamvc.entity.ProductEntity;
import com.sathyamvc.model.ProductModel;
import com.sathyamvc.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class ProductController 
{
	@Autowired
	ProductService productService;
	
	@GetMapping("/productform")
		public String getproduct() {
			return "add-product";
		}
	@PostMapping("/saveproduct")
	public String SaveProduct( ProductModel productModel) {
		productService.SaveProductDetails(productModel);
		return "success";
	}
		public String PostMethodname(ProductModel productModel) {
		System.out.println(productModel);
		return "success";
	}
		@GetMapping("/getallproducts")
		public String getallproducts(Model model) {
			List<ProductEntity> products=productService.getallproducts(model);
			model.addAttribute("products",products);
			return "product-list";
			
	}
	   @GetMapping("/getsearchform")
	   public String getSearchForm() {
	   	return "search-product";
	   }
	   
	   @PostMapping("/searchbyid")
	   public String SearchById(@RequestParam Long id,Model model) {
		   ProductEntity product=productService.SearchbyId(id);
		   model.addAttribute("product",product);
	   	return "search-product";
	   }
	   
	   @GetMapping("/delete/{id}")
	   public String deletebyid(@PathVariable("id")Long id) {
		   productService.deleteProductbyId(id);
		return "redirect:/getallproducts";
		   
	   }	   
	   
}
