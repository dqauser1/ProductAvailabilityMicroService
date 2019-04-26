package com.dqa;  

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;  
@Path("/ProductAvailability") 

public class ProductService {  
   ProductAvailabilityDao productDao = new ProductAvailabilityDao();  
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";

   
   @GET 
   @Path("/products") 
   @Produces(MediaType.APPLICATION_JSON) 
   public List<Product> getProducts(){ 
      return productDao.getAllProducts(); 
   }  

   @GET
   @Path("/products/{productid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Product getProduct(@PathParam("productid") int productid){
      return productDao.getProduct(productid);
   }
   
   @POST
   @Path("/products")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createProduct(
	  @FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("price") String price
      ) throws IOException {
	   
      Product product = new Product(id, name, price);
      int result = productDao.addProduct(product);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   
   @PUT
   @Path("/products")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateProduct(
	  @FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse
      ) throws IOException {
	   
      Product product = new Product(id, name, profession);
      int result = productDao.updateProduct(product);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
      
      @DELETE
      @Path("/products/{productid}")
      @Produces(MediaType.APPLICATION_JSON)
      public String deleteProduct(@PathParam("userid") int productid){
         int result = productDao.deleteProduct(productid);
         if(result == 1){
            return SUCCESS_RESULT;
         }
         return FAILURE_RESULT;
      }

      @OPTIONS
      @Path("/products")
      @Produces(MediaType.APPLICATION_JSON)
      public String getSupportedOperations(){
         return "<operations>GET, PUT, POST, DELETE</operations>";
      }
	  
}