package models;

import java.util.*;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;


public class Product {

	@Id
	@ObjectId
	public String _id;

	public Integer id;


	public Pricing pricing;

	public String title;

	private static JacksonDBCollection<Product, String> coll = MongoDB
			.getCollection("product", Product.class, String.class);

	public Product() {

	}

	public Product(String title) {
		this.title = title;
	}

	public static List<String> getProductIds() {
		List<Product> products = Product.coll.find().toArray();
		List<String> productIds = new ArrayList<>();
		for(Product product : products) {
			productIds.add(Integer.toString(product.id));
		}
		return productIds;
	}

	public static List<Product> all() {
		return Product.coll.find().toArray();
	}

	public static Product getProduct(Integer id) {

		DBCursor<Product> cursor = coll.find(DBQuery.all("id", id));
		if (cursor.hasNext()) {
			Product product = cursor.next();
			return product;
		}
		return null;
	}

	public static void update(String _id, Product product) {
        System.out.println("_id : " + _id);
		Product.coll.updateById(_id, product);
	}

	public static void create(Product task) {
		Product.coll.save(task);
	}

	public static void create(String label) {
		create(new Product(label));
	}

	public static void delete(String id) {
		Product task = Product.coll.findOneById(id);
		if (task != null)
			Product.coll.remove(task);
	}

	public static void removeAll() {
		Product.coll.drop();
	}

}
