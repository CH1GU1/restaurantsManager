package model;
import java.util.Comparator;

public class ClientsTelephoneComparator implements Comparator<Client> {

		@Override
		public int compare(Client client1, Client client2) {
			int comp;
			String nom1 = client1.getTelephone();
			String nom2 = client2.getTelephone();
			comp = nom1.compareToIgnoreCase(nom2);
			return comp;
		}
	}
