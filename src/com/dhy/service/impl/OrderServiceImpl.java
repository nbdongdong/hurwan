package com.dhy.service.impl;

import java.util.List;

import com.dhy.dao.OrderDao;
import com.dhy.domain.Order;
import com.dhy.domain.OrderItem;
import com.dhy.domain.PageBean;
import com.dhy.domain.User;
import com.dhy.service.OrderService;
import com.dhy.utils.BeanFactory;
import com.dhy.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService{

	@Override
	public void add(Order order) throws Exception{
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
			
			OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
			//2.向orders表中添加一个数据
			od.add(order);
			
			//int i=1/0;
			
			//3.向orderitem中添加n条数据
			for (OrderItem oi : order.getItems()) {
				od.addItem(oi);
			}
			
			//4.事务处理
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

	/**
	 * 分页查询订单
	 */
	@Override
	public PageBean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		
		// 查询当前页数据
		List<Order> list=od.findAllByPage(currPage,pageSize,user.getUid());
		
		// 查询总条数
		int totalCount=od.getTotalCount(user.getUid());
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 查看订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		return od.getById(oid);
	}

	@Override
	public void update(Order order) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		od.update(order);
	}

	/**
	 *根据状态查询订单
	 */
	@Override
	public List<Order> findAllByState(String state) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		return od.findAllByState(state);
	}

}
