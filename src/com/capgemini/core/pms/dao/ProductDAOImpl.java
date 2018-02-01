package com.capgemini.core.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.core.pms.dto.Product;
import com.capgemini.core.pms.exception.ProductManagementSystemException;
import com.capgemini.core.pms.util.DBUtil;

public class ProductDAOImpl implements IProductDAO
{
static Logger pmsLogger = Logger.getLogger(ProductDAOImpl.class.getName());
	
	public int generateProductId() throws ProductManagementSystemException{
		
		pmsLogger.debug(" generate productId method started ");
		
		int productId=0;
		try(Connection con = DBUtil.getConnection()) 
		{
			pmsLogger.info(" acquire database connection ");
		   	Statement stm = con.createStatement();
		   	ResultSet res = stm.executeQuery("select pro_id_seq.nextVal from dual");
		   	
		   	if(res.next() == false) //res.next();
		   	{
		   		pmsLogger.error(" Failed to generate id by sequence ");
		   		 throw new ProductManagementSystemException("Could not generate Product Id");
		   	}
		   	productId = res.getInt(1); // imp
		} 
		catch (Exception e) 
		{
			pmsLogger.fatal(" Insert will fail due to id generation fail ");
			//re-throwing : exception chaining
			throw new ProductManagementSystemException(e);
		}
		
		return productId;
	}

	@Override
	public int addProduct(Product product)
			throws ProductManagementSystemException {
		int productId= 0;
		try(Connection con = DBUtil.getConnection())
		{
			product.setId(generateProductId());
			
			PreparedStatement pstm = 
			                   con.prepareStatement("Insert into Product values(?,?,?,?,?)" );
			pstm.setInt(1,product.getId());
			pstm.setString(2,product.getName());
			pstm.setDouble(3, product.getPrice());
			pstm.setString(4, product.getCategory());
			
			//code below converts util date to sql date 
			long dateValue = product.getManufactureDate().getTime();
			java.sql.Date date = new java.sql.Date(dateValue);
			
			pstm.setDate(5, date );
			
			//product details inserted
			pstm.execute();
			
			productId = product.getId();
		} 
		catch (Exception e)
		{
			//re-throwing : exception chaining
			throw new ProductManagementSystemException(e);
		}
		
		return productId;
	}

	@Override
	public Product getProduct(int id) throws ProductManagementSystemException {
		
		Product product = null;
       try(Connection con = DBUtil.getConnection()) {
    	   
    	   PreparedStatement pstm = con.prepareStatement("Select * from Product where id=?");
    	   pstm.setInt(1, id);
    	  // pstm.execute();
    	   
    	   ResultSet res = pstm.executeQuery();
    	   if(res.next() == false)
    		   throw new ProductManagementSystemException("No Product Found With Id");
    	   //List<Product> products = new ArrayList<>();
    	 //  while (res.next()) {
               product = new Product();
               product.setId(res.getInt(1));
               product.setName(res.getString(2));
               product.setPrice(res.getDouble(3));
               product.setCategory(res.getString(4));
               product.setManufactureDate(res.getDate(5));
              // pros.add(pro); for list
    	  // }
       }
           
		catch (Exception e) 
		{
			//re-throwing : exception chaining
			throw new ProductManagementSystemException(e);
		}
		
		return product;
	}

/*	@Override
	public void updateProduct(Product product)
			throws ProductManagementSystemException {
		
       try(Connection con = DBUtil.getConnection())
       {
			PreparedStatement pstm = con.prepareStatement("Update Product set name=? where id=?");
			pstm.setString(1,product.getName() );
			pstm.setInt(2, product.getId());
			pstm.execute();
		} 
		catch (Exception e) {
			//re-throwing : exception chaining
			throw new ProductManagementSystemException(e);
		}  
		
	}  */
	@Override
	public void updateProduct(Product product)
			throws ProductManagementSystemException {
		
       try(Connection con = DBUtil.getConnection())
       {
			PreparedStatement pstm = con.prepareStatement("Update Product set name=?,price=?,"
					+ "category=?,ManufactureDate=? where id=?");
			pstm.setString(1,product.getName() );
			pstm.setDouble(2, product.getPrice());
			pstm.setString(3, product.getCategory());
			
			long dateValue =product.getManufactureDate().getTime();
			java.sql.Date date = new java.sql.Date(dateValue);
			
			pstm.setDate(4, date);
			pstm.setInt(5, product.getId());
			
			//System.out.println(product);
			pstm.execute();
		} 
		catch (Exception e) {
			//re-throwing : exception chaining
			throw new ProductManagementSystemException(e);
		}  
		
	} 
	@Override
	public Product removeProduct(int id) throws ProductManagementSystemException {
		Product product = null;
	   try(Connection con = DBUtil.getConnection())
	   {
		     PreparedStatement pstm1 = con.prepareStatement("Select * from Product where id=?");
    	     pstm1.setInt(1, id);
    	    
    	    ResultSet res1 = pstm1.executeQuery();
    	    
    	    if(res1.next() == false)
      		   throw new ProductManagementSystemException("No Product Found With Id");
     	     product = new Product();
             product.setId(res1.getInt(1));
             product.setName(res1.getString(2));
             product.setPrice(res1.getDouble(3));
             product.setCategory(res1.getString(4));
             product.setManufactureDate(res1.getDate(5));
             
             PreparedStatement pstm2 = con.prepareStatement("Delete from Product where id=?");
             pstm2.setInt(1, id);
             
     	    pstm2.execute();
            
		} 
	   catch (Exception e)
	   {
				//re-throwing : exception chaining
				throw new ProductManagementSystemException(e);
		}
			
			return product;
	}

	@Override
	public List<Product> getProducts() throws ProductManagementSystemException {
		
		List<Product> products = null;
		
	       try(Connection con = DBUtil.getConnection()) 
	       {
	    	   Statement stm = con.createStatement();
//	    	   PreparedStatement pstm = con.prepareStatement("Select * from Product");
//	    	   //pstm.setInt(1, id);
//	    	  // pstm.execute();
//	    	   
//	    	   ResultSet res = pstm.executeQuery();
	    	   ResultSet res =stm.executeQuery("Select * from Product order by id");
	    	   if(res.next() == false)
	    		   throw new ProductManagementSystemException("No Product Found With Id");
	    	   
	    	   
	         products = new ArrayList<>();
	    	  do {
	               Product product = new Product();
	               product.setId(res.getInt(1));
	               product.setName(res.getString(2));
	               product.setPrice(res.getDouble(3));
	               product.setCategory(res.getString(4));
	               product.setManufactureDate(res.getDate(5));
	               products.add(product);   //for list
	    	  }while (res.next() == true);
			} 
			catch (Exception e) {
				//re-throwing : exception chaining
				throw new ProductManagementSystemException(e);
			}
		
		return products;
	}


}
