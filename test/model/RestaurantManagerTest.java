package model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import exceptions.WrongNitException;

class RestaurantManagerTest {
	private RestaurantsManager restaurantsManager = new RestaurantsManager();

	public void setupScenary1() throws IOException, WrongNitException {

		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "1234", "Camilo");


	}
	public void setupScenary2() throws IOException  {

		restaurantsManager.addClient("Camilo", "Ramirez", "1548225645", 2, "3124865794", "Cali");
		restaurantsManager.addClient("Andrea", "Corrales", "2154531341", 1, "3154867624", "Cali");

	}
	@Test
	public void testSearchRestaurant_1() throws IOException, WrongNitException {
		setupScenary1();
		assertEquals("Fail test NIT", 1, restaurantsManager.searchRestaurantNit("1234"));
	}
	@Test
	public void testSearchClient_2() throws IOException {
		setupScenary2();
		assertTrue("Fail test search client", restaurantsManager.searchClientName("andrea","corrales"));
	}
}