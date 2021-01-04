package ma.ensias.ejb3.tp3;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CustomerOrderManagerRemote {
	void addCustomer(int id, String name, String email);
	List<String> getCustomers();
	int deleteCustomerByName(String name);
}
