package models;

import java.util.*;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.MongoCollection;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

@MongoCollection(name = "pricing")
public class Pricing{
    
  public double cost; 
  public double price;
  public double promo_price;
  public double savings;
  public double on_sale;
  
  
}
