package com.dhy.service;

import java.util.List;

import com.dhy.domain.Order;
import com.dhy.domain.PageBean;
import com.dhy.domain.User;

public interface OrderService {

	void add(Order order) throws Exception;

	PageBean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

}
