package com.dqa;  

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  

public class ProductAvailabilityDao { 
   public List<Product> getAllProducts(){ 
      
      List<Product> productList = null; 
      try { 
         File file = new File("ProductsAvailability.dat"); 
         if (!file.exists()) { 
            Product product = new Product(1, "iPhone10", "available"); 
            productList = new ArrayList<Product>(); 
            productList.add(product); 
            saveProductList(productList); 
         } 
         else{ 
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            productList = (List<Product>) ois.readObject(); 
            ois.close(); 
         } 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } catch (ClassNotFoundException e) { 
         e.printStackTrace(); 
      }   
      return productList; 
   } 
   
   public Product getProduct(int id){
	      List<Product> products = getAllProducts();

	      for(Product product: products){
	         if(product.getId() == id){
	            return product;
	         }
	      }
	      return null;
	}
   
   public int addProduct(Product aProduct){
	      List<Product> productList = getAllProducts();
	      boolean productExists = false;
	      for(Product product: productList){
	         if(product.getId() == aProduct.getId()){
	            productExists = true;
	            break;
	         }
	      }		
	      if(!productExists){
	         productList.add(aProduct);
	         saveProductList(productList);
	         return 1;
	      }
	      return 0;
	}
   
   
   public int updateProduct(Product aProduct){
	      List<Product> productList = getAllProducts();

	      for(Product product: productList){
	         if(product.getId() == aProduct.getId()){
	            int index = productList.indexOf(product);			
	            productList.set(index, aProduct);
	            saveProductList(productList);
	            return 1;
	         }
	      }		
	      return 0;
	}
   
   public int deleteProduct(int id){
	      List<Product> productList = getAllProducts();

	      for(Product product: productList){
	         if(product.getId() == id){
	            int index = productList.indexOf(product);			
	            productList.remove(index);
	            saveProductList(productList);
	            return 1;   
	         }
	      }		
	      return 0;
   }

   
   
   private void saveProductList(List<Product> productList){ 
      try { 
         File file = new File("ProductsAvailability.dat"); 
         FileOutputStream fos;  
         fos = new FileOutputStream(file); 
         ObjectOutputStream oos = new ObjectOutputStream(fos); 
         oos.writeObject(productList); 
         oos.close(); 
      } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
   }    
}