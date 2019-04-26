package com.dqa;  

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "user") 

public class Product implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private int id; 
   private String name; 
   private String availability;  
   public Product(){} 
    
   public Product(int id, String name, String availability){  
      this.id = id; 
      this.name = name; 
      this.availability = availability; 
   }  
   public int getId() { 
      return id; 
   }  
   @XmlElement 
   public void setId(int id) { 
      this.id = id; 
   } 
   public String getName() { 
      return name; 
   } 
   @XmlElement
   public void setName(String name) { 
      this.name = name; 
   } 
   public String getAvailability() { 
      return availability; 
   } 
   @XmlElement 
   public void setAvailability(String availability) { 
      this.availability = availability; 
   }   
   
   @Override
   public boolean equals(Object object){
      if(object == null){
         return false;
      }else if(!(object instanceof Product)){
         return false;
      }else {
         Product user = (Product)object;
         if(id == user.getId()
            && name.equals(user.getName())
            && availability.equals(user.getAvailability())
         ){
            return true;
         }			
      }
      return false;
   }	
} 