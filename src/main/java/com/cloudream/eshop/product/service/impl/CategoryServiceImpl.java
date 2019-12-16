package com.cloudream.eshop.product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudream.eshop.product.constant.RabbitQueueConstant;
import com.cloudream.eshop.product.mapper.CategoryMapper;
import com.cloudream.eshop.product.model.Category;
import com.cloudream.eshop.product.rabbitmq.RabbitMQSender;
import com.cloudream.eshop.product.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private RabbitMQSender rabbitMQSender;

	public void add(Category category,String operationType) {
		categoryMapper.add(category);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"add\",\"data_type\":\"category\",\"id\":" + category.getId() + "}");
	}

	public void update(Category category,String operationType) {
		categoryMapper.update(category);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"update\",\"data_type\":\"category\",\"id\":" + category.getId() + "}");
	}

	public void delete(Long id,String operationType) {
		categoryMapper.delete(id);
		String queue = null;
		if (Objects.isNull(operationType)) {
			queue = RabbitQueueConstant.DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_REFRESH.equals(operationType)) {
			queue = RabbitQueueConstant.REFRESH_DATA_CHANGE_QUEUE;
		} else if (RabbitQueueConstant.QUEUE_HIGH.equals(operationType)) {
			queue = RabbitQueueConstant.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		rabbitMQSender.send(queue,
				"{\"event_type\":\"delete\",\"data_type\":\"category\",\"id\":" + id + "}");
	}

	public Category findById(Long id) {
		return categoryMapper.findById(id);
	}

}
