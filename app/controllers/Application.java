package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.*;
import play.data.*;
import models.*;
import static play.libs.Json.toJson;

public class Application extends Controller {


	public static Result index() {
		return ok(views.html.index.render("Product Manager!!!"));
	}
	
	public static Result products() {

		List<Product> products = Product.all();
		return ok(toJson(products));

	}

	public static Result getProduct(Integer id) {

		Product product = Product.getProduct(id);
		return ok(toJson(product));

	}

	public static Result getProductIds() {

		return ok(toJson(Product.getProductIds()));
	}



	public static Result updateProduct() {
		JsonNode json = request().body().asJson();

		
		
		
		
		if (json == null) {
			return badRequest("Expecting Json data");
		} else {

			Product product = new Product();
			product.id = json.path("id").asInt();
			
			Product dbProduct = Product.getProduct(product.id);

			System.out.println(product.id);

			dbProduct.title = json.path("title").textValue();
			dbProduct.pricing.price  = json.path("price").asDouble();

			Product.update(dbProduct._id,dbProduct);

		}

		System.out.println("Inside Update");

		return ok(toJson(json));
	}


}
