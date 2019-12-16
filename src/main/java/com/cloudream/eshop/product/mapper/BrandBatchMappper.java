package com.cloudream.eshop.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cloudream.eshop.product.model.Brand;

@Mapper
public interface BrandBatchMappper {
	@Select("SELECT * FROM brand WHERE id IN (${ids})")
	public List<Brand> findByIds(@Param("ids") String ids);

}
