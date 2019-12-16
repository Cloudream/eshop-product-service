package com.cloudream.eshop.product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.constant.RabbitQueueConstant;
import com.cloudream.eshop.product.mapper.ProductIntroMapper;
import com.cloudream.eshop.product.model.ProductIntro;
import com.cloudream.eshop.product.rabbitmq.RabbitMQSender;
import com.cloudream.eshop.product.service.ProductIntroService;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

	@Autowired
	private ProductIntroMapper productIntroMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;

	public void add(ProductIntro productIntro, String operationType) {
		productIntroMapper.add(productIntro);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"add\",\"data_type\":\"productIntro\",\"id\":"
				+ productIntro.getId() + ",\"product_id\":" + productIntro.getProductId() + "}");
	}

	public void update(ProductIntro productIntro, String operationType) {
		productIntroMapper.update(productIntro);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"update\",\"data_type\":\"productIntro\",\"id\":"
				+ productIntro.getId() + ",\"product_id\":" + productIntro.getProductId() + "}");
	}

	public void delete(Long id, String operationType) {
		ProductIntro productIntro = findById(id);
		productIntroMapper.delete(id);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue, "{\"event_type\":\"delete\",\"data_type\":\"productIntro\",\"id\":" + id
				+ ",\"product_id\":" + productIntro.getProductId() + "}");
	}

	public ProductIntro findById(Long id) {
		return productIntroMapper.findById(id);
	}

}
