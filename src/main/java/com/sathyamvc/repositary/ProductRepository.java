package com.sathyamvc.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathyamvc.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long>{

}
