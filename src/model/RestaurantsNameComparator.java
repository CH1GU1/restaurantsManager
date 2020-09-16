package model;

import java.util.Comparator;

public class RestaurantsNameComparator implements Comparator<Restaurant>{

	@Override
	public int compare(Restaurant rest1, Restaurant rest2) {
		int comp;
		String nom1 = rest1.getName();
		String nom2 = rest2.getName();
		comp = nom1.compareToIgnoreCase(nom2);
		return comp;
	}
}