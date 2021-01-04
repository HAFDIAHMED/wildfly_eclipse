package ma.ensias.ejb3.tp3.client;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.ensias.ejb3.tp3.CustomerOrderManagerRemote;

public class CustomerOrderClient {
	public static void main(String[] args) {
		CustomerOrderClient CustomerOrderTest = new CustomerOrderClient();
		CustomerOrderTest.doTest();
	}
	
	void doTest() {
		try {
			InitialContext ic = getInitialContext();
			CustomerOrderManagerRemote CustomerOrderManagerProxy = (CustomerOrderManagerRemote) ic.lookup("ejb:/TP3EntityBean/CustomerOrderManager!ma.ensias.ejb3.tp3.CustomerOrderManagerRemote");
			CustomerOrderManagerProxy.addCustomer(5,"Abdo","abdo@mail.ma");
			System.out.println("Customer added to database");
			List<String> customers = CustomerOrderManagerProxy.getCustomers();
			for (String customer : (List<String>) customers) {
				System.out.println(customer);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
		p.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		return new InitialContext(p);
	}
		
}
