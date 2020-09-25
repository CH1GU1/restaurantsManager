package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;


import org.junit.jupiter.api.Test;


class ClientTest {
	
	private RestaurantsManager restaurantsManager = new RestaurantsManager();
	
	public void setupScenary1() throws IOException {
		restaurantsManager.addClient("Andrea", "Corrales", "2154531341", 1, "31254867624", "Cali");
		restaurantsManager.addClient("Camilo", "Ramirez", "1548225645", 4, "3124865794", "Cali");
		
	}
	@Test
	public void testClientSortedAdded_1() throws IOException {
		setupScenary1();
		//The first one added is now the second one (position 1) by add client sorted by name
		assertEquals("Fail test client", "2154531341",restaurantsManager.getClients().get(1).getIdNum());
	}
	@Test
	public void testClientFullName_2() throws IOException {
		
		setupScenary1();
		assertEquals("Fail test client full name", "Camilo Ramirez",restaurantsManager.getClients().get(0).getFullName());
	}
}
