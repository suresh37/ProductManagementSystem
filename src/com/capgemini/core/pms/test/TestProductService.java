package com.capgemini.core.pms.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.core.pms.dto.Product;
import com.capgemini.core.pms.exception.ProductManagementSystemException;
import com.capgemini.core.pms.service.IProductService;
import com.capgemini.core.pms.service.ProductServiceImpl;

public class TestProductService {

	 static IProductService productService = null;
		@BeforeClass
		public static void setUpBeforeClass() throws Exception 
		{
			productService =new ProductServiceImpl();
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception
		{
			productService = null;
		}

		@Before
		public void setUp() throws Exception {
			
		}

		@After
		public void tearDown() throws Exception {
		}

		@Test
		public void testAddProduct()  {
			Product product = new Product("Pendrive",1200,"Electronics",new Date());
			
			int id = 0;
			
			try 
			{
				id = productService.addProduct(product);
			} 
			catch (ProductManagementSystemException e)
			{
				e.printStackTrace();
			}
			assertNotEquals(0, id);
			
		}

		@Test
		public void testGetProduct() throws ProductManagementSystemException {
			Product product = null;
			
			product = productService.getProduct(1001);
			assertNotNull(product);
		}
		@Test(expected=ProductManagementSystemException.class)
		public void testGetProduct2() throws ProductManagementSystemException {
			Product product = null;
			
			product = productService.getProduct(-1002);
			assertNotNull(product);
		}

		@Test
		public void testGetProducts() throws ProductManagementSystemException 
		{
			List<Product> products = productService.getProducts();
			assertNotNull(products);
			
		}

}
