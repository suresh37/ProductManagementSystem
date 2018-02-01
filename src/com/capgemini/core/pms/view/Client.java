package com.capgemini.core.pms.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.capgemini.core.pms.dto.Product;
import com.capgemini.core.pms.exception.ProductManagementSystemException;
import com.capgemini.core.pms.service.IProductService;
import com.capgemini.core.pms.service.ProductServiceImpl;
import com.capgemini.core.pms.util.ValidationUtil;

public class Client {

	private IProductService productService = null;
	public Client() {
		productService = new ProductServiceImpl();
	}
	public void menu()
	{
		Scanner console = new Scanner(System.in);
		System.out.println("1) Add Product ");
		System.out.println("2) Get Product ");
		System.out.println("3) Update Product ");
		System.out.println("4) Remove Product ");
		System.out.println("5) View All Products ");
		System.out.println("6) Exit Product Application ");
		System.out.println("\nEnter Your Choice: ");
		int choice = console.nextInt();
		operation(choice);
	}
public void operation(int choice) {
		switch(choice)
		{
		case 1: 
			  addProduct();
			 break;
		case 2: 
			  getProduct();
			 break;
		case 3: 
			  updateProduct();
			 break;
		case 4: 
			  removeProduct();
			 break;
		case 5: 
			  getAllProduct();
			 break;
		case 6:
			 System.out.println("Good Bye!!");
			 System.exit(0);
			 
	   default:
		     System.out.println("Please Provide Valid Input!!");
		}
		
	}
private void getAllProduct()
{
	//Scanner console = new Scanner(System.in);
	List<Product> products = new ArrayList<>();
	try
	{
		products.addAll(productService.getProducts());
		Iterator<Product> it = products.iterator();
		while(it.hasNext())
		{
           Product product = it.next();
           System.out.println(product.getId()+" "+
           product.getName()+" "+
           product.getPrice()+" "+
           product.getCategory()+" "+product.getManufactureDate()+"\n");
		}
//		for (Object obj : products)
//		{
//			if( obj instanceof Product ) 	// to provide safe casting
//			{
//				  Product product= (Product)obj;
//				  System.out.println(product);
//			}
//			else
//			{
//				
//				System.out.println(obj);
//			}
//		} System.out.println("\n");
//		 System.out.println("Enter Id You what to search: ");
//		 int id = console.nextInt();
		
	    //System.out.println(productService.getProducts());
	}
	catch(ProductManagementSystemException e)
	{
		System.out.println("Something Went Wrong While trying to display all product details.Reason: "+e.getMessage());
	}
	catch(Exception e)
	{
		System.out.println("Somwthing went Wrong ");
	}
	
}
private void removeProduct() 
{
	Scanner console = new Scanner(System.in);
	System.out.println("Get Product Details");
	 System.out.println("Enter Id You what to Remove: ");
	 int id = console.nextInt();
	try 
	{
       Product product = productService.removeProduct(id);
     //productService.removeProduct(id);
         System.out.println("Product Removed is: \n");
	     System.out.println("ID: "+product.getId());
	     System.out.println("Name: "+product.getName());
	     System.out.println("Price: "+product.getPrice());
	     System.out.println("Catregory: "+product.getCategory());
	     System.out.println("Manufacture Date: "+product.getManufactureDate());
		
	}
	catch (ProductManagementSystemException e)
	{
		System.out.println("Something Went Wrong While trying to remove product details.Reason: "+e.getMessage());
		
	}
	catch (Exception e)
	{
		System.out.println("Something went Wrong");
		//System.exit(1);
	}
	
}
private void updateProduct()
{
	Scanner console = new Scanner(System.in);
	System.out.println("Update Product Details");
	System.out.println("\nEnter Product Id to update Product ");
	int id = console.nextInt();
	
	try 
	{
		Product product =productService.getProduct(id);
		
		System.out.println("Current Product Name "+product.getName());
		System.out.println("Do you want to update Name? (yes/no)");
		String reply =console.next();
		
		if(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("Enter New Name: ");
			product.setName(console.next());
		}
		
		System.out.println("Current Product Price "+product.getPrice());
		System.out.println("Do you want to update Price? (yes/no)");
		reply =console.next();
		if(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("Enter New Price: ");
			product.setPrice(console.nextDouble());
		}
		
		System.out.println("Current Product Category "+product.getCategory());
		System.out.println("Do you want to update Category? (yes/no)");
		reply =console.next();
		if(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("Enter New Catgeory: ");
			product.setCategory(console.next());
		}
		
		System.out.println("Current Product Manufacture Date "+product.getManufactureDate());
		System.out.println("Do you want to update Manufacture Date? (yes/no)");
		reply =console.next();
		if(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("Enter New Manufacture Date (dd-MM-yyyy): ");
			//convert string to date
			String date = console.next();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(date,format);
			Date mDate = java.sql.Date.valueOf(localDate);
			
			product.setManufactureDate(mDate);
			
		}
		productService.updateProduct(product);
		System.out.println("Product Updated successfully");
	}
	catch (ProductManagementSystemException e) 
	{
		System.out.println("Something Went Wrong While trying to update product details.Reason: "+e.getLocalizedMessage());
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		//System.out.println("Something went Wrong");
	}
	
	
	/*Scanner console = new Scanner(System.in);
	System.out.println("Update Product Details");
	System.out.println("Enter Name you want to Change: ");
    String name=console.next();
    System.out.println("Enter ID in which you want to Change: ");
    int id=console.nextInt();
	try 
	{
		Product pro = new Product();
		pro.setId(id);
		pro.setName(name);
		productService.updateProduct(pro);
	} 
	catch (ProductManagementSystemException e) 
	{
		System.out.println("Something Went Wrong While trying to update product details.Reason: "+e.getLocalizedMessage());
	}
	catch (Exception e) 
	{
		System.out.println("Something went Wrong");
	} */
	
}
private void getProduct() 
{
	Scanner console = new Scanner(System.in);
	System.out.println("Get Product Details");
	 System.out.println("Enter Id You what to search: ");
	 int id = console.nextInt();
	try
	{
	     Product product = productService.getProduct(id);
	     
	     System.out.println("ID: "+product.getId());
	     System.out.println("Name: "+product.getName());
	     System.out.println("Price: "+product.getPrice());
	     System.out.println("Catregory: "+product.getCategory());
	     System.out.println("Manufacture Date: "+product.getManufactureDate());
			
		
	    System.out.println(productService.getProduct(id));
	}
	catch(ProductManagementSystemException e)
	{
		System.out.println("Something Went Wrong while trying to get Product Details"+e.getMessage());
	}
	catch(Exception e)
	{
		System.out.println("Somwthing went Wrong ");
	}
	
}
private void addProduct() {
	Scanner console = new Scanner(System.in);
	System.out.println("Add Product Details: ");
	String productName;
	do
	{
	System.out.println("1)Enter Product Name: ");
    productName = console.next();
	}while(ValidationUtil.isProductNameInvalid(productName));
	
	System.out.println("2)Enter Price: ");
	double producPrice = console.nextDouble();
	System.out.println("3)Enter Category: ");
	String category = console.next();
	System.out.println("4)Enter Manufacturing Date(dd-MM-yyyy): ");
	String date = console.next();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate localDate = LocalDate.parse(date, format);
	Date mDate = java.sql.Date.valueOf(localDate);
	
	//Create DTO 
	Product product = new Product(productName,producPrice,category,mDate);
	
	//pass to service
	try 
	{
		int proId = productService.addProduct(product);
		System.out.println("Product Details Successfully Added ");
		System.out.println("Product Id: "+proId);
	} 
	catch (ProductManagementSystemException e) 
	{
		System.out.println("Something Went Wrong While trying to insert product details.Reason: "+e.getLocalizedMessage());
	}
	catch (Exception e) 
	{   //e.printStackTrace();
		System.out.println("Something went Wrong");
	}
	
}

public static void main(String[] args) 
{
	PropertyConfigurator.configure("log4j.properties");
	
	Client client = new Client();
	
	while(true)
	{
		client.menu();
	}
}
}
