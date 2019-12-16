package com.cloudream.eshop.product.batches.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.batches.BrandBatchService;
import com.cloudream.eshop.product.mapper.BrandBatchMappper;
import com.cloudream.eshop.product.model.Brand;

@Service
public class BrandBatchServiceImpl implements BrandBatchService {
	@Autowired
	private BrandBatchMappper brandBatchMapper;

	@Override
	public List<Brand> findByIds(String ids) {
		return brandBatchMapper.findByIds(ids);
	}

}
