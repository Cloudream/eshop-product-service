package com.cloudream.eshop.product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.constant.RabbitQueueConstant;
import com.cloudream.eshop.product.mapper.ProductPropertyMapper;
import com.cloudream.eshop.product.model.ProductProperty;
import com.cloudream.eshop.product.rabbitmq.RabbitMQSender;
import com.cloudream.eshop.product.service.ProductPropertyService;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

	@Autowired
	private ProductPropertyMapper productPropertyMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;

	public void add(ProductProperty productProperty, String operationType) {
		productPropertyMapper.add(productProperty);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"add\",\"data_type\":\"productProperty\",\"id\":"
				+ productProperty.getId() + ",\"product_id\":" + productProperty.getProductId() + "}");
	}

	public void update(ProductProperty productProperty, String operationType) {
		productPropertyMapper.update(productProperty);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"update\",\"data_type\":\"productProperty\",\"id\":"
				+ productProperty.getId() + ",\"product_id\":" + productProperty.getProductId() + "}");
	}

	public void delete(Long id, String operationType) {
		ProductProperty productProperty = findById(id);
		productPropertyMapper.delete(id);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"delete\",\"data_type\":\"productProperty\",\"id\":" + id
				+ ",\"product_id\":" + productProperty.getProductId() + "}");
	}

	public ProductProperty findById(Long id) {
		return productPropertyMapper.findById(id);
	}

	@Override
	public ProductProperty findByProductId(Long productId) {
		return productPropertyMapper.findByProductId(productId);
	}

}
