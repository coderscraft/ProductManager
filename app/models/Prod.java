package models;

import java.util.*;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

public class Prod{
    
  
	public String _id;
	
	
  public String id;
  
  public String name;
  
  public String cost;

  

  private static JacksonDBCollection<Prod, String> coll = MongoDB.getCollection("prod", Prod.class, String.class);

  public Prod(){

  }

  
  public static List<Prod> all() {
    return Prod.coll.find().toArray();
  }

  public static void create(Prod task) {
    Prod.coll.save(task);
  }

  
  public static void delete(String id) {
    Prod task = Prod.coll.findOneById(id);
    if (task != null)
        Prod.coll.remove(task);
  }

  public static void removeAll(){
    Prod.coll.drop();
  }

}
