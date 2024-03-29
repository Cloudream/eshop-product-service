package com.cloudream.eshop.product.service;

import com.cloudream.eshop.product.model.ProductProperty;

public interface ProductPropertyService {

	public void add(ProductProperty productProperty, String operationType);

	public void update(ProductProperty productProperty, String operationType);

	public void delete(Long id, String operationType);

	public ProductProperty findById(Long id);
	
	public ProductProperty findByProductId(Long productId);

}
