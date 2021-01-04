package ma.ensias.ejb3.tp2.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.ensias.ejb3.tp2.ShoppingCartBeanRemote;

public class ShoppingCartClient {
	public ShoppingCartClient( ) {
	}
	public static void main(String[] args) {
		ShoppingCartClient shoppingCartTest = new ShoppingCartClient();
		shoppingCartTest.doTest();
	}
	InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
		p.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		return new InitialContext(p);
	}
	void doTest() {
		try {
			InitialContext ctx = getInitialContext();
			System.out.println("ShoppingCart Lookup");
			ShoppingCartBeanRemote shoppingCartProxy = (ShoppingCartBeanRemote) ctx.lookup("ejb:/TP2StatefulBean/ShoppingCartBean!ma.ensias.ejb3.tp2.ShoppingCartBeanRemote?stateful");
			System.out.println("Adding Book Items...");
			shoppingCartProxy.addBookItem("Java coding rules");
			shoppingCartProxy.addBookItem("EJB3 developers guide");
			System.out.println("Printing Cart Items:");
			ArrayList<String> cartItems = shoppingCartProxy.getCartItems();
			for (String book : (ArrayList<String>) cartItems) {
				System.out.println(book);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
