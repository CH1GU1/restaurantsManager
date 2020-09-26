package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class ProductTest {

	private RestaurantsManager restaurantsManager = new RestaurantsManager();

	public void setupScenary1() throws IOException{

		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		restaurantsManager.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		restaurantsManager.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
	}
	
	@Test
	public void testProductAddedForEachRestaurant_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test product added to restaurant", "12345",restaurantsManager.getProducts().get(0).getRestaurantNit());

	}
	@Test
	public void testProductSameCode_2() throws IOException {
		setupScenary1();
		assertEquals("Fail test product with same code added to restaurant", 1,restaurantsManager.getProducts().size());
	}
}
