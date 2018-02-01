package com.capgemini.core.pms.service;

import java.util.List;

import com.capgemini.core.pms.dao.IProductDAO;
import com.capgemini.core.pms.dao.ProductDAOImpl;
import com.capgemini.core.pms.dto.Product;
import com.capgemini.core.pms.exception.ProductManagementSystemException;

public class ProductServiceImpl implements IProductService {

	// Loose Coupling :: Association
		private IProductDAO ProductDAO = null;
		
		 public ProductServiceImpl() 
		{
			ProductDAO = new ProductDAOImpl();
		}

		@Override
		public int addProduct(Product product)
				throws ProductManagementSystemException {
			
			return ProductDAO.addProduct(product);
		}

		@Override
		public Product getProduct(int id) throws ProductManagementSystemException {
			
			return ProductDAO.getProduct(id);
		}

		@Override
		public void updateProduct(Product product)
				throws ProductManagementSystemException {
			 ProductDAO.updateProduct(product);

		}

		@Override
		public Product removeProduct(int id)
				throws ProductManagementSystemException {
			
			return ProductDAO.removeProduct(id);
		}

		@Override
		public List<Product> getProducts() throws ProductManagementSystemException {
			
			return ProductDAO.getProducts();
		}

}
