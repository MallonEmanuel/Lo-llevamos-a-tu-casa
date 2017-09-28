package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.List;
import java.util.Random;

import unpsjb.labprog.tp2.springmvc.model.City;
import unpsjb.labprog.tp2.springmvc.model.Customer;

/**
 * Esta clase se ocupa de generar domicilios para los clientes.
 * @author emanuel
 */

public class AddressGenerator {

	private List<Customer> customerList;
	private List<City> cityList;
	private Random random;

	public AddressGenerator(List<Customer> customerList, List<City> cityList) {
		this.cityList = cityList;
		this.customerList = customerList;
		random = new Random();
	}
	
	public String generate() {
		String s = "INSERT INTO address (id,active,name,city_id,customer_id) VALUES";
		int addressId = 1;
		for (int i = 0; i < customerList.size(); i++) {
			Customer customer = customerList.get(i);
			int addressCount = random.nextInt(3) + 1;
			for (int j = 0; j < addressCount; j++) {
				s += "(" + (addressId++) + ",true,'" + generateAddress() + "'," + generateCity() + ","
						+ customer.getId() + ")";
				if (i + 1 == customerList.size() && j + 1 == addressCount) {
					s += ";";
				} else {
					s += ",";
				}
			}
		}
		return s;
	}

	private long generateCity() {
		int cityPosition = random.nextInt(cityList.size());
		return cityList.get(cityPosition).getId();
	}

	private String[] bassicAdrress = { "Alem", "Villarino", "Tierra del Fuego", "Gales", "España", "Sarmiento",
			"9 de julio", "20 de julio", "25 de mayo", "Alvear" };

	private String generateAddress() {
		int bassicAddresPos = random.nextInt(bassicAdrress.length);
		int numberAddress = random.nextInt(2000) + 1;
		return bassicAdrress[bassicAddresPos] + " " + numberAddress;
	}

}
