package ma.ensias.ejb3.tp2;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface ShoppingCartBeanLocal {
	void addBookItem(String book);
	void removeBookItem(String book);
	void setCartItems(ArrayList<String> cartItems);
	ArrayList<String> getCartItems();
}
