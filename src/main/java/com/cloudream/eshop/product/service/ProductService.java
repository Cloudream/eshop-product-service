package com.cloudream.eshop.product.service;

import com.cloudream.eshop.product.model.Product;

public interface ProductService {

	public void add(Product product, String operationType);

	public void update(Product product, String operationType);

	public void delete(Long id, String operationType);

	public Product findById(Long id);

}
