package com.cloudream.eshop.product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.constant.RabbitQueueConstant;
import com.cloudream.eshop.product.mapper.ProductSpecificationMapper;
import com.cloudream.eshop.product.model.ProductSpecification;
import com.cloudream.eshop.product.rabbitmq.RabbitMQSender;
import com.cloudream.eshop.product.service.ProductSpecificationService;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

	@Autowired
	private ProductSpecificationMapper productSpecificationMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;

	public void add(ProductSpecification productSpecification,String operationType) {
		productSpecificationMapper.add(productSpecification);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"add\",\"data_type\":\"productSpecification\",\"id\":" + productSpecification.getId()
						+ ",\"product_id\":" + productSpecification.getProductId() + "}");
	}

	public void update(ProductSpecification productSpecification,String operationType) {
		productSpecificationMapper.update(productSpecification);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"update\",\"data_type\":\"productSpecification\",\"id\":"
						+ productSpecification.getId() + ",\"product_id\":" + productSpecification.getProductId()
						+ "}");
	}

	public void delete(Long id, String operationType) {
		ProductSpecification productSpecification = findById(id);
		productSpecificationMapper.delete(id);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"delete\",\"data_type\":\"productSpecification\",\"id\":" + id + ",\"product_id\":"
						+ productSpecification.getProductId() + "}");
	}

	public ProductSpecification findById(Long id) {
		return productSpecificationMapper.findById(id);
	}
	
	public ProductSpecification findByProductId(Long productId) {
		return productSpecificationMapper.findByProductId(productId);
	}

}
