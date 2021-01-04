package ma.ensias.ejb3.tp3;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CustomerOrderManagerLocal {
	void addCustomer(int id, String name, String email);
	List<String> getCustomers();
	int deleteCustomerByName(String name);
}
