package ma.ensias.ejb3.tp2;

import java.util.ArrayList;

import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
/**
 * Session Bean implementation class ShoppingCartBean
 */
@Stateful
@LocalBean
public class ShoppingCartBean implements ShoppingCartBeanRemote, ShoppingCartBeanLocal {
   
	/**
     * Default constructor. 
     */
    public ShoppingCartBean() {
        // TODO Auto-generated constructor stub
    }
    private ArrayList<String> cartItems = new ArrayList<String>();
    int bookOrder = 0;
    public void addBookItem(String book) {
    	bookOrder++;
    	cartItems.add(book + "( Book number = " + new Integer(bookOrder).toString() + ")");
    }
    public void removeBookItem(String book) {
    	cartItems.remove(book);
    }
    public void setCartItems(ArrayList<String> cartItems) {
    	this.cartItems = cartItems;
    }
    public ArrayList<String> getCartItems() {
    	return cartItems;
    }
    @PreDestroy
    public void goodBye () {
    	System.out.println ("From goodBye method with @PreDestroy annotation");
    }
    @PrePassivate
    public void passivateShoppingCart () {
    	System.out.println("From passivateShoppingCart method with @PrePassivate annotation");
    }
    @PostActivate
    public void activateShoppingCart () {
    	System.out.println ("From activateShoppingCart method with @PostActivate annotation");
    }
    
    @Remove
    public void stopSession() {
    	System.out.println("From stopSession method with @Remove annotation");
    }
}
