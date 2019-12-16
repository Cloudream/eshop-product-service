package com.cloudream.eshop.product.batches;

import java.util.List;

import com.cloudream.eshop.product.model.Brand;

public interface BrandBatchService {
	List<Brand> findByIds(String ids);
}
