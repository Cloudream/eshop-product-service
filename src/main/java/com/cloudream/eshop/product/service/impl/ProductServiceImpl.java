package com.cloudream.eshop.product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.constant.RabbitQueueConstant;
import com.cloudream.eshop.product.mapper.ProductMapper;
import com.cloudream.eshop.product.model.Product;
import com.cloudream.eshop.product.rabbitmq.RabbitMQSender;
import com.cloudream.eshop.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private RabbitMQSender rabbitMQSender;

	public void add(Product product, String operationType) {
		productMapper.add(product);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"add\",\"data_type\":\"product\",\"id\":" + product.getId() + "}");
	}

	public void update(Product product, String operationType) {
		productMapper.update(product);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"update\",\"data_type\":\"product\",\"id\":" + product.getId() + "}");
	}

	public void delete(Long id, String operationType) {
		productMapper.delete(id);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"delete\",\"data_type\":\"product\",\"id\":" + id + "}");
	}

	public Product findById(Long id) {
		return productMapper.findById(id);
	}

}
