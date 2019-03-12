package com.dhy.service.impl;

import java.util.List;

import com.dhy.dao.ProductDao;
import com.dhy.dao.impl.ProductDaoImpl;
import com.dhy.domain.PageBean;
import com.dhy.domain.Product;
import com.dhy.service.ProductService;
import com.dhy.utils.BeanFactory;

public class ProductServiceImpl implements ProductService{

	/**
	 * 查询最新
	 */
	@Override
	public List<Product> findNew() throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pdao.findNew();
	}

	/**
	 * 查询热门
	 */
	@Override
	public List<Product> findHot() throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pdao.findHot();
	}

	/**
	 * 查询单个商品
	 */
	@Override
	public Product getByPid(String pid) throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pdao.getByPid(pid);
	}

	/**
	 * 按类别分页查询商品
	 */
	@Override
	public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		//当前页数据
		List<Product> list=pdao.findByPage(currPage,pageSize,cid);
		
		//总条数
		int totalCount = pdao.getTotalCount(cid);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 查询所有
	 */
	@Override
	public List<Product> findAll() throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		
		return pdao.findAll();
	}

	/**
	 * 添加商品
	 */
	@Override
	public void add(Product p) throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		pdao.add(p);
	}

}
